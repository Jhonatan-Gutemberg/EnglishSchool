package com.englishSchool.app.service.interfaces;

import java.util.List;

import com.englishSchool.app.dto.TeacherDTO;
import com.englishSchool.app.model.Teacher;

public interface ITeacherService {

     Teacher register(TeacherDTO teacherDTO);

    List<Teacher> getAllTeacher();

    Teacher getTeacherById(Long id);

    Teacher update(Long id, TeacherDTO teacherDTO);

    boolean delete(Long id);
    
}
