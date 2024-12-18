package com.englishSchool.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.englishSchool.app.model.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    
}
