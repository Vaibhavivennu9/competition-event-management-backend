package com.example.backend.controller;

import com.example.backend.dto.competition.CompetitionRequest;
import com.example.backend.dto.competition.CompetitionResponse;
import com.example.backend.service.CompetitionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/competitions")
@RequiredArgsConstructor
public class CompetitionController {

    private final CompetitionService competitionService;

    // Create competition
    @PostMapping
    public ResponseEntity<CompetitionResponse> createCompetition(
            @Valid @RequestBody CompetitionRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(competitionService.createCompetition(request));
    }

    // Get all competitions
    @GetMapping
    public ResponseEntity<List<CompetitionResponse>> getAllCompetitions() {

        return ResponseEntity.ok(
                competitionService.getAllCompetitions()
        );
    }

    // Get competition by ID
    @GetMapping("/{id}")
    public ResponseEntity<CompetitionResponse> getCompetitionById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                competitionService.getCompetitionById(id)
        );
    }

    // Delete competition
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompetition(
            @PathVariable Integer id) {

        competitionService.deleteCompetition(id);

        return ResponseEntity.noContent().build();
    }
}