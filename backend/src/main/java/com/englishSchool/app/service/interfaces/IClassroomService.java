package com.englishSchool.app.service.interfaces;

import java.util.List;

import com.englishSchool.app.dto.ClassroomDTO;
import com.englishSchool.app.model.Classroom;

public interface IClassroomService {

    Classroom register(ClassroomDTO classroomDTO);

    List<Classroom> getAllClassroom();

    Classroom getClassroomById(Long id);

    Classroom update(Long id, ClassroomDTO classroomDTO);

    Classroom addTeacher(Long id, ClassroomDTO classroomDTO,Long id_Teacher);

    boolean delete(Long id);
}
