package com.example.backend.service;

import com.example.backend.entity.TeamMember;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.repository.TeamMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamMemberService {

    private final TeamMemberRepository teamMemberRepository;

    public TeamMemberService(TeamMemberRepository teamMemberRepository) {
        this.teamMemberRepository = teamMemberRepository;
    }

    // Create team member
    public TeamMember createTeamMember(TeamMember teamMember) {
        return teamMemberRepository.save(teamMember);
    }
    // Add participant to a team
    public TeamMember addTeamMember(TeamMember teamMember) {
        return teamMemberRepository.save(teamMember);
    }
    // Get all team members
    public List<TeamMember> getAllTeamMembers() {
        return teamMemberRepository.findAll();
    }

    // Get team member by ID
    public TeamMember getTeamMemberById(Integer id) {

        return teamMemberRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Team member not found with id: " + id
                        ));
    }

    // Get all members of a particular team
    public List<TeamMember> getMembersByTeam(Integer teamId) {

        return teamMemberRepository.findByTeamId(teamId);
    }

    // Delete team member
    public void deleteTeamMember(Integer id) {

        if (!teamMemberRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Team member not found with id: " + id
            );
        }

        teamMemberRepository.deleteById(id);
    }
}
