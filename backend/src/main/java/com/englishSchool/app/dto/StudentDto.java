package com.englishSchool.app.dto;

import java.time.LocalDateTime;
public record StudentDto(
        String name,
        LocalDateTime lastRewarded,
        String email,
        String phoneNumber,
        String address,
        String cpf,
        String rg,
        String level,
        Long id_classroom) {

}
