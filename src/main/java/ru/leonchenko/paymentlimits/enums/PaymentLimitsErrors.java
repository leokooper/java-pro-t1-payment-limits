package ru.leonchenko.paymentlimits.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.PAYMENT_REQUIRED;

@Getter
@RequiredArgsConstructor
public enum PaymentLimitsErrors {

    COMMON_ERROR(INTERNAL_SERVER_ERROR, "COMMON_ERROR", "Непредвиденная ошибка. Пожалуйста, повторите позже"),
    USER_NOT_FOUND_ERROR(NOT_FOUND, "USER_NOT_FOUND_ERROR", "Пользователь по указанному id не найден"),
    NOT_ENOUGH_BALANCE_ERROR(PAYMENT_REQUIRED, "NOT_ENOUGH_BALANCE_ERROR", "Недостаточно средств для совершения операции");

    private final HttpStatus status;
    private final String code;
    private final String message;
}