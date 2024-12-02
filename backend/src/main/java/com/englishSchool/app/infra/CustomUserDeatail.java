package com.englishSchool.app.infra;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.englishSchool.app.model.Users;
import com.englishSchool.app.repositories.UsersRepository;

public class CustomUserDeatail implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    
        Users users = this.usersRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(users.getEmail(), users.getPassword(), new ArrayList<>());
    }
    
}
