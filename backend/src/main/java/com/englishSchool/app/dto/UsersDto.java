package com.englishSchool.app.dto;

import java.time.LocalDateTime;

import com.englishSchool.app.enums.UsersType;

public record UsersDto(
        String name,
        LocalDateTime lastRewarded,
        String email,
        UsersType type,
        String phoneNumber,
        String password,
        String address) {

}
