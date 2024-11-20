package com.englishSchool.app.dto;

import java.time.LocalDate;

public record ActivityDTO(
        String name,
        String description,
        String difficultyLevel,
        LocalDate dueDate,
        Long studentId) {

}
