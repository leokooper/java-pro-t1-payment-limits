package ru.leonchenko.paymentlimits.models.userlimit;

import java.math.BigDecimal;

public record UserLimitRqDto(Long userId, BigDecimal limit) {}