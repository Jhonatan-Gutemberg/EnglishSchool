package com.englishSchool.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.englishSchool.app.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    
}
