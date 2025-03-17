package ru.leonchenko.paymentlimits.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.leonchenko.paymentlimits.entity.LimitTransaction;
import ru.leonchenko.paymentlimits.entity.UserLimit;
import ru.leonchenko.paymentlimits.exception.PaymentLimitsException;
import ru.leonchenko.paymentlimits.mapper.UserLimitMapper;
import ru.leonchenko.paymentlimits.models.userlimit.UserLimitRsDto;
import ru.leonchenko.paymentlimits.repository.LimitTransactionRepository;
import ru.leonchenko.paymentlimits.repository.UserLimitRepository;
import java.math.BigDecimal;
import java.util.List;
import static ru.leonchenko.paymentlimits.enums.PaymentLimitsErrors.NOT_ENOUGH_BALANCE_ERROR;
import static ru.leonchenko.paymentlimits.enums.PaymentLimitsErrors.USER_NOT_FOUND_ERROR;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserLimitService {

    private final UserLimitMapper userLimitMapper;
    private final UserLimitRepository userLimitRepository;
    private final LimitTransactionRepository limitTransactionRepository;

    @Transactional(readOnly = false)
    public UserLimitRsDto getLimitByUserId(Long userId) {
        val userLimit = findLimitByUserId(userId);
        return userLimitMapper.toUserLimitRsDto(userLimit);
    }

    @Transactional(readOnly = false)
    public UserLimitRsDto updateLimit(Long userId, BigDecimal amount) {

        var userLimit = findLimitByUserId(userId);

        val limit = userLimit.getLimit();

        val isEnoughBalance = limit.compareTo(amount) > 0 || limit.compareTo(amount) == 0;

        if (!isEnoughBalance){
            throw new PaymentLimitsException(NOT_ENOUGH_BALANCE_ERROR);
        }

        val newLimit = userLimit.getLimit().subtract(amount);
        userLimit.setLimit(newLimit);
        val newUserLimit = userLimitRepository.save(userLimit);

        val limitTransaction = new LimitTransaction(amount, userId);
        limitTransactionRepository.save(limitTransaction);

        return userLimitMapper.toUserLimitRsDto(newUserLimit);
    }

    @Transactional(readOnly = false)
    public UserLimitRsDto restoreLimit(Long transactionId) {
        val limitTransaction = limitTransactionRepository
                .findById(transactionId)
                .orElseThrow(() -> new PaymentLimitsException(USER_NOT_FOUND_ERROR));
        val userId = limitTransaction.getUserId();
        val amount = limitTransaction.getAmount();
        var userlimit = findLimitByUserId(userId);
        userlimit.setLimit(userlimit.getLimit().add(amount));
        val newUserLimit = userLimitRepository.save(userlimit);
        return userLimitMapper.toUserLimitRsDto(newUserLimit);
    }

    @Scheduled(cron = "${cron.expression.reset.limits}")
    public void resetDailyLimits() {
        List<UserLimit> userLimits = userLimitRepository.findAll();
        for (UserLimit userLimit : userLimits) {
            userLimit.setLimit(new BigDecimal("10000.00"));
            userLimitRepository.save(userLimit);
        }
    }

    private UserLimit findLimitByUserId(Long userId) {
        val userLimit = userLimitRepository.findByUserId(userId);
        if (userLimit.isEmpty()) {
            var newUserLimit = new UserLimit(userId);
            return userLimitRepository.save(newUserLimit);
        } else {
            return userLimit.get();
        }
    }
}