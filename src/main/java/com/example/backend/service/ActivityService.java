package com.example.backend.service;

import com.example.backend.entity.Activity;
import com.example.backend.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public Activity createActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    public Activity getActivityById(Integer id) {
        return activityRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Activity not found"));
    }
    // Get activities belonging to a competition
    public List<Activity> getActivitiesByCompetition(
            Integer competitionId) {

        return activityRepository
                .findByCompetitionId(competitionId);
    }

    public void deleteActivity(Integer id) {
        activityRepository.deleteById(id);
    }
}