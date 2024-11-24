package com.englishSchool.app.service.interfaces;

import java.util.List;

import com.englishSchool.app.dto.Activity.ActivityCompletionRequest;
import com.englishSchool.app.dto.Activity.ActivityDTO;
import com.englishSchool.app.model.Activity;

public interface IActivityService {
    Activity register(ActivityDTO ActivityDTO);

    List<Activity> getAllActivity();

    Activity getActivityById(Long id);

    Activity update(Long id, ActivityDTO ActivityDTO);

    boolean delete(Long id);

    String confirmActivityCompletion(ActivityCompletionRequest request);

    String checkActivityStatus(Long studentId, Long activityId);
}
