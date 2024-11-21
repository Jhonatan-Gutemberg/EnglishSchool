package com.englishSchool.app.mappers;


import com.englishSchool.app.dto.ActivityDTO;
import com.englishSchool.app.model.Activity;

public class ActivityMapper {

    public static Activity ActivityDtoToModel(ActivityDTO activityDTO) {
        return new Activity(activityDTO.name(),activityDTO.description(),activityDTO.difficultyLevel());
    }
    
}
