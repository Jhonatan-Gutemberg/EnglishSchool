package com.englishSchool.app.service.interfaces;

import java.util.List;

import com.englishSchool.app.dto.ActivityDTO;
import com.englishSchool.app.model.Activity;

public interface IActivityService {
    Activity register(ActivityDTO ActivityDTO);

    List<Activity> getAllActivity();

    Activity getActivityById(Long id);

    Activity update(Long id, ActivityDTO ActivityDTO);

    boolean delete(Long id);
}
