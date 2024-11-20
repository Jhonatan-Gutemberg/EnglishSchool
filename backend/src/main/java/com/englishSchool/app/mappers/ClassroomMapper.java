package com.englishSchool.app.mappers;

import com.englishSchool.app.dto.ClassroomDTO;
import com.englishSchool.app.model.Classroom;

public class ClassroomMapper {

    public static Classroom ClassroomDtoToModel(ClassroomDTO classroomDTO) {
        return new Classroom(classroomDTO.name());
    }
    
}
