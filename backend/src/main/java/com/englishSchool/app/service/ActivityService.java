package com.englishSchool.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.englishSchool.app.dto.ActivityDTO;
import com.englishSchool.app.exceptions.UserNotFoundException;
import com.englishSchool.app.mappers.ActivityMapper;
import com.englishSchool.app.model.Activity;
import com.englishSchool.app.model.Classroom;
import com.englishSchool.app.repositories.ActivityRepository;
import com.englishSchool.app.service.interfaces.IActivityService;
import com.englishSchool.app.service.interfaces.IClassroomService;

@Service
public class ActivityService implements IActivityService {

    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private IClassroomService classroomService;

    @Override
    public Activity register(ActivityDTO activityDTO) {
        Activity activity = ActivityMapper.ActivityDtoToModel(activityDTO);
        Classroom classroom = classroomService.getClassroomById(activityDTO.id_classroom());
        activity.setClassroom(classroom);
        activityRepository.save(activity);
        return activity;
    }

    @Override
    public List<Activity> getAllActivity() {
        return activityRepository.findAll();
    }

    @Override
    public Activity getActivityById(Long id) {
        Optional<Activity> Activity = activityRepository.findById(id);

        return Activity.orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found."));
    }

    @Override
    public Activity update(Long id, ActivityDTO activityDTO) {
        Optional<Activity> optional = activityRepository.findById(id);
        if (optional.isPresent()) {
            Activity existingActivity = optional.get();
            existingActivity.setName(activityDTO.name());
            existingActivity.setDescription(activityDTO.description());
            return existingActivity;
        } else {
            throw new UserNotFoundException("User with id " + id + " not found.");
        }
    }

    @Override
    public boolean delete(Long id) {
        Optional<Activity> optional = activityRepository.findById(id);
        if (optional.isPresent()) {
            activityRepository.delete(optional.get());
            return true;
        }

        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
