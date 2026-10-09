package com.example.backend.controller;

import com.example.backend.entity.Activity;
import com.example.backend.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    // Create activity
    @PostMapping
    public ResponseEntity<Activity> createActivity(
            @RequestBody Activity activity) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(activityService.createActivity(activity));
    }

    // Get all activities
    @GetMapping
    public ResponseEntity<List<Activity>> getAllActivities() {

        return ResponseEntity.ok(
                activityService.getAllActivities()
        );
    }

    // Get activity by ID
    @GetMapping("/{id}")
    public ResponseEntity<Activity> getActivityById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                activityService.getActivityById(id)
        );
    }

    // Get activities belonging to a competition
    @GetMapping("/competition/{competitionId}")
    public ResponseEntity<List<Activity>> getActivitiesByCompetition(
            @PathVariable Integer competitionId) {

        return ResponseEntity.ok(
                activityService.getActivitiesByCompetition(competitionId)
        );
    }

    // Delete activity
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(
            @PathVariable Integer id) {

        activityService.deleteActivity(id);

        return ResponseEntity.noContent().build();
    }
}