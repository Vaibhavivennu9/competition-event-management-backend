package com.example.backend.controller;

import com.example.backend.dto.participant.ParticipantRequest;
import com.example.backend.dto.participant.ParticipantResponse;
import com.example.backend.service.ParticipantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participants")
@RequiredArgsConstructor
public class ParticipantController {

    private final ParticipantService participantService;

    // Create participant
    @PostMapping
    public ResponseEntity<ParticipantResponse> createParticipant(
            @Valid @RequestBody ParticipantRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(participantService.createParticipant(request));
    }

    // Get all participants
    @GetMapping
    public ResponseEntity<List<ParticipantResponse>> getAllParticipants() {

        return ResponseEntity.ok(
                participantService.getAllParticipants()
        );
    }

    // Get participant by ID
    @GetMapping("/{id}")
    public ResponseEntity<ParticipantResponse> getParticipantById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                participantService.getParticipantById(id)
        );
    }

    // Delete participant
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParticipant(
            @PathVariable Integer id) {

        participantService.deleteParticipant(id);

        return ResponseEntity.noContent().build();
    }
}