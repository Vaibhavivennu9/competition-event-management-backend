package com.example.backend.controller;

import com.example.backend.entity.Team;
import com.example.backend.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    // Create team
    @PostMapping
    public ResponseEntity<Team> createTeam(
            @RequestBody Team team) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(teamService.createTeam(team));
    }

    // Get all teams
    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() {

        return ResponseEntity.ok(
                teamService.getAllTeams()
        );
    }

    // Get team by ID
    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                teamService.getTeamById(id)
        );
    }

    // Get teams belonging to a competition
    @GetMapping("/competition/{competitionId}")
    public ResponseEntity<List<Team>> getTeamsByCompetition(
            @PathVariable Integer competitionId) {

        return ResponseEntity.ok(
                teamService.getTeamsByCompetition(competitionId)
        );
    }

    // Delete team
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(
            @PathVariable Integer id) {

        teamService.deleteTeam(id);

        return ResponseEntity.noContent().build();
    }
}