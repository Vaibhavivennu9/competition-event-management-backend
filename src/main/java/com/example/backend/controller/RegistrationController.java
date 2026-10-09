package com.example.backend.controller;

import com.example.backend.dto.registration.RegistrationRequest;
import com.example.backend.dto.registration.RegistrationResponse;
import com.example.backend.service.RegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registrations")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    // Create registration
    @PostMapping
    public ResponseEntity<RegistrationResponse> createRegistration(
            @Valid @RequestBody RegistrationRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(registrationService.createRegistration(request));
    }

    // Get all registrations
    @GetMapping
    public ResponseEntity<List<RegistrationResponse>> getAllRegistrations() {

        return ResponseEntity.ok(
                registrationService.getAllRegistrations()
        );
    }

    // Get registration by ID
    @GetMapping("/{id}")
    public ResponseEntity<RegistrationResponse> getRegistrationById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                registrationService.getRegistrationById(id)
        );
    }

    // Get registrations for an activity
    @GetMapping("/activity/{activityId}")
    public ResponseEntity<List<RegistrationResponse>> getRegistrationsByActivity(
            @PathVariable Integer activityId) {

        return ResponseEntity.ok(
                registrationService.getRegistrationsByActivity(activityId)
        );
    }

    // Delete registration
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegistration(
            @PathVariable Integer id) {

        registrationService.deleteRegistration(id);

        return ResponseEntity.noContent().build();
    }
}