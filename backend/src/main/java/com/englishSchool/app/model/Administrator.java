package com.englishSchool.app.model;

import java.time.LocalDateTime;

import com.englishSchool.app.enums.UsersType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_admin")
@Getter
@Setter
@NoArgsConstructor
public class Administrator extends Users {
    @Column(name = "lastLoginAt")
    private LocalDateTime lastLoginAt;

    public Administrator(String name, LocalDateTime lastRewarded, String email, UsersType type, String phoneNumber,
            String password, String address, LocalDateTime lastLoginAt) {
        super(name, lastRewarded, email, type, phoneNumber, password, address);
        this.lastLoginAt = lastLoginAt;
    }
}
