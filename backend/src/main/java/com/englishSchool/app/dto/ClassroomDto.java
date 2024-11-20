package com.englishSchool.app.dto;

import com.englishSchool.app.model.Teacher;

public record ClassroomDto(
        String name,
        Teacher teacher) {

}
