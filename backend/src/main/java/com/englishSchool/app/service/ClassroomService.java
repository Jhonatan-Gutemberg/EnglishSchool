package com.englishSchool.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.englishSchool.app.dto.ClassroomDTO;
import com.englishSchool.app.mappers.ClassroomMapper;
import com.englishSchool.app.model.Activity;
import com.englishSchool.app.model.Classroom;
import com.englishSchool.app.model.Teacher;
import com.englishSchool.app.exceptions.UserNotFoundException;
import com.englishSchool.app.repositories.ClassroomRepository;
import com.englishSchool.app.service.interfaces.IClassroomService;
import com.englishSchool.app.service.interfaces.ITeacherService;

@Service
public class ClassroomService implements IClassroomService {

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private ITeacherService teacherService;

    @Override
    public Classroom register(ClassroomDTO classroomDTO, Long id_Teacher) {
        Teacher teacher = teacherService.getTeacherById(id_Teacher);

        if (teacher != null) {
            Classroom classroom = ClassroomMapper.ClassroomDtoToModel(classroomDTO);
            classroom.setTeacher(teacher);
            classroomRepository.save(classroom);
            return classroom;
        } else {
            throw new UserNotFoundException("User with id " + id_Teacher + " not found.");

        }

    }

    @Override
    public List<Classroom> getAllClassroom() {
        return classroomRepository.findAll();
    }

    @Override
    public Classroom getClassroomById(Long id) {
        Optional<Classroom> Classroom = classroomRepository.findById(id);

        return Classroom.orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found."));

    }

    @Override
    public List<Classroom> getClassroomsByTeacherId(Long teacherId) {
        Teacher teacher = teacherService.getTeacherById(teacherId);

        if (teacher != null) {
            return classroomRepository.findByTeacherId(teacherId);
        } else {
            throw new UserNotFoundException("Teacher with id " + teacherId + " not found.");
        }
    }

    @Override
    public Classroom update(Long id, ClassroomDTO classroomDTO) {
        Optional<Classroom> optional = classroomRepository.findById(id);
        if (optional.isPresent()) {
            Classroom existingClassroom = optional.get();
            existingClassroom.setName(classroomDTO.name());
            classroomRepository.save(existingClassroom);
            return existingClassroom;
        } else {
            throw new UserNotFoundException("User with id " + id + " not found.");
        }
    }

    @Override
    public boolean delete(Long id) {
        Optional<Classroom> optional = classroomRepository.findById(id);
        if (optional.isPresent()) {
            classroomRepository.delete(optional.get());
            return true;
        }

        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Classroom addTeacher(Long id, ClassroomDTO classroomDTO, Long id_Teacher) {
        Optional<Classroom> optional = classroomRepository.findById(id);

        if (optional.isPresent()) {
            Classroom existingClassroom = optional.get();

            Teacher teacher = teacherService.getTeacherById(id_Teacher);

            existingClassroom.setTeacher(teacher);
            classroomRepository.save(existingClassroom);
            return existingClassroom;
        } else {
            throw new UserNotFoundException("User with id " + id + " not found.");
        }
    }

    @Override
    public List<Activity> getActivitiesByClassroomId(Long classroomId) {
        Classroom classroom = getClassroomById(classroomId);
        return classroom.getActivities();

    }

    @Override
    public int getStudentCountByClassroomId(Long classroomId) {
        Classroom classroom = getClassroomById(classroomId);

        return classroom.getStudentCount();
    }

}
