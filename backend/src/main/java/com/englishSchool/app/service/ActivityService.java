package com.englishSchool.app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.englishSchool.app.dto.Activity.ActivityCompletionRequest;
import com.englishSchool.app.dto.Activity.ActivityDTO;
import com.englishSchool.app.enums.ActivityStatus;
import com.englishSchool.app.exceptions.UserNotFoundException;
import com.englishSchool.app.mappers.ActivityMapper;
import com.englishSchool.app.model.Activity;
import com.englishSchool.app.model.Classroom;
import com.englishSchool.app.model.Student;
import com.englishSchool.app.model.StudentActivity;
import com.englishSchool.app.repositories.ActivityRepository;
import com.englishSchool.app.repositories.StudentActivityRepository;
import com.englishSchool.app.service.interfaces.IActivityService;
import com.englishSchool.app.service.interfaces.IClassroomService;
import com.englishSchool.app.service.interfaces.IStudentService;

@Service
public class ActivityService implements IActivityService {

    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private IClassroomService classroomService;
    @Autowired
    private StudentActivityRepository studentActivityRepository;
    @Autowired
    private IStudentService studentService;

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

    @Override
    public String confirmActivityCompletion(ActivityCompletionRequest request) {
        Long studentId = request.studentId();
        Long activityId = request.activityId();

        Student student = studentService.getStudentById(studentId);

        Activity activity = getActivityById(activityId);

        if (!activity.getClassroom().equals(student.getClassroom())) {
            return "The activity does not belong to this student's class.";
        }

        Optional<StudentActivity> studentActivityOptional = studentActivityRepository
                .findByStudentIdAndActivityId(studentId, activityId);

        StudentActivity studentActivity;
        if (!studentActivityOptional.isPresent()) {
            studentActivity = new StudentActivity();
            studentActivity.setStudent(student);
            studentActivity.setActivity(activity);
            studentActivity.setStatus(ActivityStatus.PENDING);
            studentActivityRepository.save(studentActivity);
        } else {
            studentActivity = studentActivityOptional.get();
        }

        studentActivity.setStatus(ActivityStatus.COMPLETED);
        studentActivity.setCompletionDate(LocalDateTime.now());

        studentActivityRepository.save(studentActivity);

        return "Activity completed successfully!";
    }

    @Override
    public String checkActivityStatus(Long studentId, Long activityId) {
        Optional<StudentActivity> studentActivityOptional = studentActivityRepository
                .findByStudentIdAndActivityId(studentId, activityId);

        if (studentActivityOptional.isPresent()) {
            StudentActivity studentActivity = studentActivityOptional.get();
            if (studentActivity.getStatus() == ActivityStatus.COMPLETED) {
                return "COMPLETED";
            } else {
                return null;
            }
        }

        return "Activity not found for this student.";
    }

}
