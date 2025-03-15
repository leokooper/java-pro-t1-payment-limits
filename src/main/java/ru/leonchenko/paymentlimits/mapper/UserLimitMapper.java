package ru.leonchenko.paymentlimits.mapper;

import ru.leonchenko.paymentlimits.annotation.Mapper;
import ru.leonchenko.paymentlimits.entity.UserLimit;
import ru.leonchenko.paymentlimits.models.userlimit.UserLimitRsDto;

@Mapper
public class UserLimitMapper {

    public UserLimitRsDto toUserLimitRsDto(UserLimit userLimit) {
        return new UserLimitRsDto(
                userLimit.getId(),
                userLimit.getLimit(),
                userLimit.getUserId()
        );
    }
}