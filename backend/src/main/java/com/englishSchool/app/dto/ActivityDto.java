package com.englishSchool.app.dto;

import java.time.LocalDate;

public record ActivityDto(
        String name,
        String description,
        String difficultyLevel,
        LocalDate dueDate,
        Long studentId) {

}
