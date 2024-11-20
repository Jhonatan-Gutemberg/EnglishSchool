package com.englishSchool.app.dto;

import java.time.LocalDate;

import com.englishSchool.app.enums.UsersType;

public record StudentDTO(
                String name,
                LocalDate lastRewarded,
                String email,
                 UsersType type,
                String phoneNumber,
                String password,
                String address,
                String cpf,
                String rg,
                String level,
                Long id_classroom) {

}
