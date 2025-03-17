package ru.leonchenko.paymentlimits.models.userlimit;

import java.math.BigDecimal;

public record UserLimitRsDto(Long id, BigDecimal limit, Long userId) {}