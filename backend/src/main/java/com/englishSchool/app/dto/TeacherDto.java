package com.englishSchool.app.dto;

import java.time.LocalDateTime;

public record TeacherDto(
        String name,
        LocalDateTime lastRewarded,
        String email,
        String phoneNumber,
        String address,
        double salary,
        String department,
        Long id_classrooms
) {
    
}
