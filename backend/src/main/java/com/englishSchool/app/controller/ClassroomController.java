package com.englishSchool.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.englishSchool.app.controller.ApiResponse.ApiResponse;
import com.englishSchool.app.dto.ClassroomDTO;
import com.englishSchool.app.model.Classroom;
import com.englishSchool.app.service.interfaces.IClassroomService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/classroom")
@RequiredArgsConstructor
public class ClassroomController {

    @Autowired
    private IClassroomService classroomService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Classroom>> register(
            @RequestBody @Valid ClassroomDTO classroomDTO) {
        try {
            Classroom classroom = classroomService.register(classroomDTO);
            ApiResponse<Classroom> response = new ApiResponse<Classroom>(true,
                    "User registered succesfully",
                    classroom);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            ApiResponse<Classroom> errorResponse = new ApiResponse<Classroom>(false,
                    e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Classroom>>> getAllClassroom() {
        try {
            List<Classroom> Classroom = classroomService.getAllClassroom();
            ApiResponse<List<Classroom>> response = new ApiResponse<>(true,
                    "All Classroom fetched successfully", Classroom);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<List<Classroom>> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Classroom>> getClassroomById(@PathVariable Long id) {
        try {
            Classroom Classroom = classroomService.getClassroomById(id);
            if (Classroom != null) {
                ApiResponse<Classroom> response = new ApiResponse<>(true,
                        "Classroom found successfully",
                        Classroom);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Classroom> response = new ApiResponse<>(false, "Classroom not found",
                        null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<Classroom> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Classroom>> update(@PathVariable Long id,
            @RequestBody @Valid ClassroomDTO ClassroomDTO) {
        try {
            Classroom updatedClassroom = classroomService.update(id,
                    ClassroomDTO);
            if (updatedClassroom != null) {
                ApiResponse<Classroom> response = new ApiResponse<>(true,
                        "Classroom updated successfully",
                        updatedClassroom);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Classroom> response = new ApiResponse<>(false, "Classroom not found",
                        null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<Classroom> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PostMapping("/update/{id}/{idTeacher}")
    public ResponseEntity<ApiResponse<Classroom>> addTeacher(@PathVariable Long id,
            @RequestBody @Valid ClassroomDTO ClassroomDTO,@PathVariable Long idTeacher) {
        try {
            Classroom updatedClassroom = classroomService.addTeacher(id, ClassroomDTO, idTeacher);
            if (updatedClassroom != null) {
                ApiResponse<Classroom> response = new ApiResponse<>(true,
                        "Classroom updated successfully",
                        updatedClassroom);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Classroom> response = new ApiResponse<>(false, "Classroom not found",
                        null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<Classroom> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        try {
            boolean isDeleted = classroomService.delete(id);
            if (isDeleted) {
                ApiResponse<Void> response = new ApiResponse<>(true, "Partner Company deleted successfully", null);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Void> response = new ApiResponse<>(false, "Partner Company not found", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<Void> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

}
