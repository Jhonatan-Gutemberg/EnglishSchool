package com.englishSchool.app.service.interfaces;

import java.util.List;

import com.englishSchool.app.dto.StudentDTO;
import com.englishSchool.app.model.Student;

public interface IStudentService {
    Student register(StudentDTO studentDTO);

    List<Student> getAllStudent();

    Student getStudentById(Long id);

    Student update(Long id, StudentDTO studentDTO);

    boolean delete(Long id);
}
