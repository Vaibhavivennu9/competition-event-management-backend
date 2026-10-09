package com.example.backend.service;

import com.example.backend.dto.participant.ParticipantRequest;
import com.example.backend.dto.participant.ParticipantResponse;
import com.example.backend.entity.Participant;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.repository.ParticipantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public ParticipantResponse createParticipant(ParticipantRequest request) {

        if (participantRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException(
                    "Participant with this email already exists"
            );
        }
        Participant participant = new Participant();

        participant.setName(request.getName());
       // participant.setStudentId(request.getStudentId());
        participant.setEmail(request.getEmail());
        participant.setPhone(request.getPhone());
        participant.setInstitution(request.getInstitution());

        // Save entity
        Participant savedParticipant =
                participantRepository.save(participant);

        // Entity → Response DTO
        return convertToResponse(savedParticipant);
        //return participantRepository.save(participant);
    }

    public List<ParticipantResponse> getAllParticipants() {
        return participantRepository.findAll()
                .stream()
                .map(this::convertToResponse).toList();
    }

    public ParticipantResponse getParticipantById(Integer id) {
        Participant participant= participantRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Participant not found"));
        return convertToResponse(participant);
    }

    public void deleteParticipant(Integer id) {
        if (!participantRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Participant not found with id: " + id
            );
        }
        participantRepository.deleteById(id);
    }
    private ParticipantResponse convertToResponse(
            Participant participant) {

        ParticipantResponse response =
                new ParticipantResponse();

        response.setId(participant.getId());
        response.setName(participant.getName());
        response.setEmail(participant.getEmail());
        response.setPhone(participant.getPhone());
        response.setInstitution(participant.getInstitution());

        return response;
    }
}