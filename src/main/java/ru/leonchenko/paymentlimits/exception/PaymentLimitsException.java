package ru.leonchenko.paymentlimits.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import ru.leonchenko.paymentlimits.enums.PaymentLimitsErrors;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Getter
@JsonIgnoreProperties({"status", "stackTrace", "suppressed", "localizedMessage", "cause"})
public class PaymentLimitsException extends RuntimeException {

    private static final String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss";

    private final HttpStatus status;
    private final String uuid;
    private final String time;
    private final String code;

    public PaymentLimitsException(HttpStatus status, String uuid, String time, String code, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
        this.uuid = uuid;
        this.time = time;
        this.code = code;
    }

    public PaymentLimitsException(PaymentLimitsErrors error) {
        this(error.getStatus(), UUID.randomUUID().toString(), DateTimeFormatter.ofPattern(DATE_TIME_FORMAT).format(LocalDateTime.now()), error.getCode(), error.getMessage(), null);
    }

    public PaymentLimitsException(PaymentLimitsErrors error, String uuid) {
        this(error.getStatus(), uuid, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT).format(LocalDateTime.now()), error.getCode(), error.getMessage(), null);
    }
}