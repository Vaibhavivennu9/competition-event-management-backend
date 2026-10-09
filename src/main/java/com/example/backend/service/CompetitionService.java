package com.example.backend.service;
import com.example.backend.dto.competition.CompetitionRequest;
import com.example.backend.dto.competition.CompetitionResponse;
import com.example.backend.entity.Competition;
import com.example.backend.enums.CompetitionStatus;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.repository.CompetitionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetitionService {

    private final CompetitionRepository competitionRepository;

    public CompetitionService(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    public CompetitionResponse createCompetition(
            CompetitionRequest request) {

        // DTO → Entity
        Competition competition = new Competition();

        competition.setName(request.getName());
        competition.setDescription(request.getDescription());
        competition.setStartDate(request.getStartDate());
        competition.setEndDate(request.getEndDate());

        // Set initial status
        competition.setStatus(CompetitionStatus.UPCOMING);

        // Entity → Database
        Competition savedCompetition =
                competitionRepository.save(competition);

        // Entity → Response DTO
        return convertToResponse(savedCompetition);
    }

    // Get all competitions
    public List<CompetitionResponse> getAllCompetitions() {

        return competitionRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }


    // Get competition by ID
    public CompetitionResponse getCompetitionById(
            Integer id) {

        Competition competition =
                competitionRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Competition not found with id: "
                                                + id
                                ));

        return convertToResponse(competition);
    }


    // Delete competition
    public void deleteCompetition(Integer id) {

        // First check whether competition exists
        competitionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Competition not found with id: "
                                        + id
                        ));

        competitionRepository.deleteById(id);
    }


    // Convert Entity → Response DTO
    private CompetitionResponse convertToResponse(
            Competition competition) {

        CompetitionResponse response =
                new CompetitionResponse();

        response.setId(competition.getId());
        response.setName(competition.getName());
        response.setDescription(competition.getDescription());
        response.setStartDate(competition.getStartDate());
        response.setEndDate(competition.getEndDate());
        response.setStatus(competition.getStatus());

        return response;
    }
}
