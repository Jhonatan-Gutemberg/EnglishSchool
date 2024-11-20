package com.englishSchool.app.model;

import java.time.LocalDate;
import java.util.List;

import com.englishSchool.app.enums.UsersType;

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

    @Column(name = "salary", nullable = false)
    private double salary;
    @Column(name = "department", nullable = false)
    private String department;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<Classroom> classrooms;

    public Teacher(String name, LocalDate lastRewarded, String email, UsersType type, String phoneNumber,
            String password, String address, double salary, String department) {
        super(name, lastRewarded, email, type, phoneNumber, password, address);
        this.salary = salary;
        this.department = department;
    }

    

}
