package com.englishSchool.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.englishSchool.app.model.Classroom;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    
}
