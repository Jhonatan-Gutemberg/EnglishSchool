package com.englishSchool.app.mappers;

import com.englishSchool.app.dto.AdministratorDTO;
import com.englishSchool.app.dto.StudentDTO;
import com.englishSchool.app.dto.TeacherDTO;
import com.englishSchool.app.enums.UsersType;
import com.englishSchool.app.model.Administrator;
import com.englishSchool.app.model.Student;
import com.englishSchool.app.model.Teacher;
import com.englishSchool.app.model.Users;

public class UsersMapper {

    public static Users UserDtoToModel(StudentDTO userDTO) {
        return new Users(userDTO.name(), userDTO.databirth(), userDTO.email(), userDTO.cpf(), UsersType.USERS,
                userDTO.phoneNumber(), userDTO.password(), userDTO.address());
    }

    public static Student StudentDtoToModel(StudentDTO studentDTO) {
        return new Student(studentDTO.name(), studentDTO.databirth(), studentDTO.email(), studentDTO.cpf(),
                UsersType.STUDENT, studentDTO.phoneNumber(), studentDTO.password(), studentDTO.address(),
                studentDTO.rg(), studentDTO.level());
    }

    public static Teacher TeacherDtoToModel(TeacherDTO teacherDTO) {
        return new Teacher(teacherDTO.name(), teacherDTO.databirth(), teacherDTO.email(), teacherDTO.cpf(),
                UsersType.TEACHER, teacherDTO.phoneNumber(), teacherDTO.password(), teacherDTO.address(),
                teacherDTO.department());
    }

    public static Administrator UserDtoToModel(AdministratorDTO administratorDTO) {
        return new Administrator(administratorDTO.name(), administratorDTO.databirth(), administratorDTO.email(),
                administratorDTO.cpf(), UsersType.ADMIN, administratorDTO.phoneNumber(), administratorDTO.password(),
                administratorDTO.address(), administratorDTO.lastLoginAt());
    }

}
