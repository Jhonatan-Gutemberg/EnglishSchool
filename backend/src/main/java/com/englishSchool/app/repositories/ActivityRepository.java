package com.englishSchool.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.englishSchool.app.model.Activity;

@Repository
public interface ActivityRepository  extends JpaRepository<Activity, Long>{
    
}
