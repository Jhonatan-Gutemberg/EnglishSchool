package com.englishSchool.app.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.englishSchool.app.enums.UsersType;

public record AdministratorDTO(
        String name,
        LocalDate lastRewarded,
        String email,
        UsersType type,
        String phoneNumber,
        String password,
        String address,
        LocalDateTime lastLoginAt
        ) {

}
