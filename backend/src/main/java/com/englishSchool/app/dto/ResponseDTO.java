package com.englishSchool.app.dto;

import com.englishSchool.app.enums.UsersType;

public record ResponseDTO(
        Long id,
        String name,
        UsersType type,
        String token) {

}
