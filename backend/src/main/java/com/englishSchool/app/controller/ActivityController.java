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
import com.englishSchool.app.dto.Activity.ActivityCompletionRequest;
import com.englishSchool.app.dto.Activity.ActivityDTO;
import com.englishSchool.app.model.Activity;
import com.englishSchool.app.service.interfaces.IActivityService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/activity")
@RequiredArgsConstructor
public class ActivityController {

    @Autowired
    private IActivityService activityService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Activity>> register(
            @RequestBody @Valid ActivityDTO activityDTO) {
        try {
            Activity activity = activityService.register(activityDTO);
            ApiResponse<Activity> response = new ApiResponse<Activity>(true,
                    "User registered succesfully",
                    activity);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            ApiResponse<Activity> errorResponse = new ApiResponse<Activity>(false,
                    e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Activity>>> getAllActivity() {
        try {
            List<Activity> activity = activityService.getAllActivity();
            ApiResponse<List<Activity>> response = new ApiResponse<>(true,
                    "All Activity fetched successfully", activity);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<List<Activity>> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Activity>> getActivityById(@PathVariable Long id) {
        try {
            Activity Activity = activityService.getActivityById(id);
            if (Activity != null) {
                ApiResponse<Activity> response = new ApiResponse<>(true,
                        "Activity found successfully",
                        Activity);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Activity> response = new ApiResponse<>(false, "Activity not found",
                        null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<Activity> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Activity>> update(@PathVariable Long id,
            @RequestBody @Valid ActivityDTO ActivityDTO) {
        try {
            Activity updatedActivity = activityService.update(id,
                    ActivityDTO);
            if (updatedActivity != null) {
                ApiResponse<Activity> response = new ApiResponse<>(true,
                        "Activity updated successfully",
                        updatedActivity);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Activity> response = new ApiResponse<>(false, "Activity not found",
                        null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<Activity> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        try {
            boolean isDeleted = activityService.delete(id);
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

    @PostMapping("/confirmCompletion")
    public ResponseEntity<ApiResponse<String>> confirmActivityCompletion(
            @RequestBody ActivityCompletionRequest request) {
        try {
            String resultMessage = activityService.confirmActivityCompletion(request);

            ApiResponse<String> response = new ApiResponse<>(true, resultMessage, null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<String> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/{studentId}/{activityId}/status")
    public String checkActivityStatus(@PathVariable Long studentId, @PathVariable Long activityId) {
        return activityService.checkActivityStatus(studentId, activityId);
    }

}
