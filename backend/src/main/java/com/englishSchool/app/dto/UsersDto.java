package com.englishSchool.app.dto;

import java.time.LocalDate;

import com.englishSchool.app.enums.UsersType;

public record UsersDTO(
        String name,
        LocalDate databirth,
        String email,
        String cpf,
        UsersType type,
        String phoneNumber,
        String password,
        String address) {

}
