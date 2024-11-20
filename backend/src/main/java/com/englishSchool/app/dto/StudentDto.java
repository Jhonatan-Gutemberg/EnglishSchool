package com.englishSchool.app.dto;

import java.time.LocalDate;

public record StudentDTO(
                String name,
                LocalDate lastRewarded,
                String email,
                String phoneNumber,
                String password,
                String address,
                String cpf,
                String rg,
                String level,
                Long id_classroom) {

}
