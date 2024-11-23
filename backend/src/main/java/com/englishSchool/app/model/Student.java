package com.englishSchool.app.model;

import java.time.LocalDate;
import java.util.List;

import com.englishSchool.app.enums.UsersType;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_student")
@Getter
@Setter
@NoArgsConstructor
public class Student extends Users {

    @Column(name = "rg", nullable = false, unique = true, columnDefinition = "VARCHAR(255)")
    private String rg;
    @Column(name = "level", nullable = false, columnDefinition = "VARCHAR(255)")
    private String level;

    @ManyToOne
    @JsonBackReference
    private Classroom classroom;

    public Student(String name, LocalDate databirth, String email, String cpf, UsersType type, String phoneNumber,
            String password, String address, String rg, String level) {
        super(name, databirth, email, cpf, type, phoneNumber, password, address);
        this.rg = rg;
        this.level = level;
    }

}
