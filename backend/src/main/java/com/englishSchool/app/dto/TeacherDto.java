package com.englishSchool.app.dto;

import java.time.LocalDate;

public record TeacherDTO(
        String name,
        LocalDate lastRewarded,
        String email,
        String phoneNumber,
        String password,
        String address,
        double salary,
        String department,
        Long id_classrooms
) {
    
}
