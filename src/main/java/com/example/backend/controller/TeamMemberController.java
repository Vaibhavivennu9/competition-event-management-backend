package com.example.backend.controller;

import com.example.backend.entity.TeamMember;
import com.example.backend.service.TeamMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/team-members")
@RequiredArgsConstructor
public class TeamMemberController {

    private final TeamMemberService teamMemberService;

    // Add participant to a team
    @PostMapping
    public ResponseEntity<TeamMember> addTeamMember(
            @RequestBody TeamMember teamMember) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(teamMemberService.addTeamMember(teamMember));
    }

    // Get all team members
    @GetMapping
    public ResponseEntity<List<TeamMember>> getAllTeamMembers() {

        return ResponseEntity.ok(
                teamMemberService.getAllTeamMembers()
        );
    }

    // Get team member by ID
    @GetMapping("/{id}")
    public ResponseEntity<TeamMember> getTeamMemberById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                teamMemberService.getTeamMemberById(id)
        );
    }

    // Get members of a particular team
    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<TeamMember>> getMembersByTeam(
            @PathVariable Integer teamId) {

        return ResponseEntity.ok(
                teamMemberService.getMembersByTeam(teamId)
        );
    }

    // Remove participant from team
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeamMember(
            @PathVariable Integer id) {

        teamMemberService.deleteTeamMember(id);

        return ResponseEntity.noContent().build();
    }
}