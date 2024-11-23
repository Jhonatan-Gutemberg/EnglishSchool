package com.englishSchool.app.model;

import java.time.LocalDate;
import java.util.List;

import com.englishSchool.app.enums.UsersType;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_teacher")
@Getter
@Setter
@NoArgsConstructor
public class Teacher extends Users {

    @Column(name = "cpf", nullable = false)
    private String cpf;
    @Column(name = "department", nullable = false)
    private String department;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Classroom> classrooms;

    public Teacher(String name, LocalDate lastRewarded, String email, UsersType type, String phoneNumber,
            String password, String address, String cpf, String department) {
        super(name, lastRewarded, email, type, phoneNumber, password, address);
        this.cpf = cpf;
        this.department = department;
    }

    

}
