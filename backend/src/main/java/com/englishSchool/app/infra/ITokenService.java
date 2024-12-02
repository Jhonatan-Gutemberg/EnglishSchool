package com.englishSchool.app.infra;

import com.englishSchool.app.model.Users;

public interface ITokenService {

    String generateToken(Users users);
    String validateToken(String token);
    
}
