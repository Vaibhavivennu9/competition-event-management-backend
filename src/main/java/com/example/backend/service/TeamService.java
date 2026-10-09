
package com.example.backend.service;
import com.example.backend.entity.Competition;
import com.example.backend.entity.Team;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.repository.CompetitionRepository;
import com.example.backend.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Team getTeamById(Integer id) {
        return teamRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Team not found with id: " + id
                        ));
    }

    public List<Team> getTeamsByCompetition(Integer competitionId) {
        return teamRepository.findByCompetitionId(competitionId);
    }

    public void deleteTeam(Integer id) {
        if (!teamRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Team not found with id: " + id
            );
        }

        teamRepository.deleteById(id);
    }
}