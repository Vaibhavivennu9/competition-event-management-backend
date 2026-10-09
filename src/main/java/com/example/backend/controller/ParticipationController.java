package com.example.backend.controller;

import com.example.backend.dto.participation.ParticipationResponse;
import com.example.backend.service.ParticipationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/participations")
@RequiredArgsConstructor
public class ParticipationController {

    private final ParticipationService participationService;

    // Event-day check-in
    @PostMapping("/check-in")
    public ResponseEntity<ParticipationResponse> checkIn(
            @RequestParam Integer registrationId,
            @RequestParam Integer activityId) {

        return ResponseEntity.ok(
                participationService.checkIn(
                        registrationId,
                        activityId
                )
        );
    }

    // Get participation by registration
    @GetMapping("/registration/{registrationId}")
    public ResponseEntity<ParticipationResponse>
    getParticipationByRegistration(
            @PathVariable Integer registrationId) {

        return ResponseEntity.ok(
                participationService.getParticipationByRegistration(
                        registrationId
                )
        );
    }
}