package com.englishSchool.app.dto;

import java.time.LocalDate;

public record ActivityDTO(
        String name,
        String description,
        int difficultyLevel,
        LocalDate dueDate,
        Long id_classroom) {

}
