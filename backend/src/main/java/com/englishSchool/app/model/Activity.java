package com.englishSchool.app.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
    @Column(name = "description", nullable = false, columnDefinition = "VARCHAR(255)")
    private String description;
    @Column(name = "difficultyLevel", nullable = false, columnDefinition = "VARCHAR(255)")
    private int difficultyLevel;
    @Column(name = "dueDate", nullable = false, columnDefinition = "VARCHAR(255)")
    private LocalDate createdDate;
    @ManyToOne
    @JoinColumn(name = "classroom_id", nullable = false)
    @JsonBackReference
    private Classroom classroom;

    public Activity(String name, String description, int difficultyLevel) {
        this.name = name;
        this.description = description;
        this.difficultyLevel = difficultyLevel;
        this.createdDate = LocalDate.now();
    }
}
