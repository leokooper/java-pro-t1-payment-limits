package ru.leonchenko.paymentlimits.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.leonchenko.paymentlimits.models.userlimit.UserLimitRqDto;
import ru.leonchenko.paymentlimits.models.userlimit.UserLimitRsDto;
import ru.leonchenko.paymentlimits.service.UserLimitService;


@RestController
@RequestMapping("/api/v1/limits")
@RequiredArgsConstructor
public class UserLimitController {

    private final UserLimitService userLimitService;

    @GetMapping("/users/{userId}")
    public UserLimitRsDto getLimitByUserId(@PathVariable Long userId) {
        return userLimitService.getLimitByUserId(userId);
    }

    @PutMapping("/users/{userId}")
    public UserLimitRsDto updateLimit(
            @PathVariable Long userId,
            @RequestBody UserLimitRqDto request) {
        return userLimitService.updateLimit(userId, request.limit());
    }

    @PostMapping("/restore/transaction/{transactionId}")
    public UserLimitRsDto restoreLimit(@PathVariable Long transactionId) {
        return userLimitService.restoreLimit(transactionId);
    }
}