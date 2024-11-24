package com.englishSchool.app.model;

import java.time.LocalDateTime;

import com.englishSchool.app.enums.ActivityStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentActivity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;
    
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "activity_id", referencedColumnName = "id")
    private Activity activity;
    
    @Enumerated(EnumType.STRING) 
    private ActivityStatus status;
    private LocalDateTime completionDate;  
    
    
}
