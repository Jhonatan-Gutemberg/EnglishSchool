package com.englishSchool.app.dto.Activity;

import java.time.LocalDate;

public record ActivityDTO(
        String name,
        String description,
        int difficultyLevel,
        LocalDate dueDate,
        Long id_classroom) {

}
