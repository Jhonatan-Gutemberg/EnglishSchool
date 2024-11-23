package com.englishSchool.app.dto;

import java.time.LocalDate;

import com.englishSchool.app.enums.UsersType;

public record TeacherDTO(
                String name,
                LocalDate databirth,
                String email,
                UsersType type,
                String phoneNumber,
                String password,
                String address,
                String cpf,
                String department,
                Long id_classrooms) {

}
