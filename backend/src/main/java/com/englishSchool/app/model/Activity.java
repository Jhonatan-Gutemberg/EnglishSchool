package com.englishSchool.app.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_activity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)")
    private String name;
    @Column(name = "description", nullable = false, unique = true, columnDefinition = "VARCHAR(255)")
    private String description;
    @Column(name = "difficultyLevel", nullable = false, unique = true, columnDefinition = "VARCHAR(255)")
    private String difficultyLevel;
    @Column(name = "dueDate", nullable = false, unique = true, columnDefinition = "VARCHAR(255)")
    private LocalDate dueDate;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
