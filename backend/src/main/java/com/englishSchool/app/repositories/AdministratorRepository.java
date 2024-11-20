package com.englishSchool.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.englishSchool.app.model.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long>{
    
}
