package com.englishSchool.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.englishSchool.app.model.StudentActivity;

@Repository
public interface StudentActivityRepository extends JpaRepository<StudentActivity, Long> {

    Optional<StudentActivity> findByStudentIdAndActivityId(Long studentId, Long activityId);


}
