package com.englishSchool.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.englishSchool.app.controller.ApiResponse.ApiResponseLogin;
import com.englishSchool.app.dto.LoginDTO;
import com.englishSchool.app.dto.ResponseDTO;
import com.englishSchool.app.infra.ITokenService;
import com.englishSchool.app.model.Users;
import com.englishSchool.app.repositories.UsersRepository;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final ITokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponseLogin<ResponseDTO>> login(@RequestBody LoginDTO loginDTO) {
        try {
            Users user = this.usersRepository.findByEmail(loginDTO.email())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            if (passwordEncoder.matches(loginDTO.password(), user.getPassword())) {
                String token = this.tokenService.generateToken(user);

                ResponseDTO responseDTO = new ResponseDTO(user.getId(),user.getName(), user.getType(), token);
                ApiResponseLogin<ResponseDTO> response = new ApiResponseLogin<>(true, "Login successful", responseDTO,
                        token);

                return ResponseEntity.ok(response);
            } else {
                ApiResponseLogin<ResponseDTO> errorResponse = new ApiResponseLogin<>(false, "Invalid password", null,
                        null);
                return ResponseEntity.badRequest().body(errorResponse);
            }

        } catch (Exception e) {
            ApiResponseLogin<ResponseDTO> errorResponse = new ApiResponseLogin<>(false, e.getMessage(), null, null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

}