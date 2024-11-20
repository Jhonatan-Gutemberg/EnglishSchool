package com.englishSchool.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.englishSchool.app.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

}
