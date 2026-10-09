package com.example.backend.service;

import com.example.backend.dto.registration.RegistrationRequest;
import com.example.backend.dto.registration.RegistrationResponse;
import com.example.backend.entity.Activity;
import com.example.backend.entity.Participant;
import com.example.backend.entity.Registration;
import com.example.backend.entity.Team;
import com.example.backend.enums.RegistrationStatus;
import com.example.backend.enums.RegistrationType;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.repository.ActivityRepository;
import com.example.backend.repository.ParticipantRepository;
import com.example.backend.repository.RegistrationRepository;
import com.example.backend.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final ActivityRepository activityRepository;
    private final ParticipantRepository participantRepository;
    private final TeamRepository teamRepository;


    // Create registration
    public RegistrationResponse createRegistration(
            RegistrationRequest request) {

        // 1. Find activity
        Activity activity =
                activityRepository.findById(request.getActivityId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Activity not found with id: "
                                                + request.getActivityId()
                                ));


        // 2. Individual registration
        if (request.getRegistrationType()
                == RegistrationType.INDIVIDUAL) {

            // Participant is required
            if (request.getParticipantId() == null) {
                throw new RuntimeException(
                        "Participant is required for individual registration"
                );
            }

            // Team should not be provided
            if (request.getTeamId() != null) {
                throw new RuntimeException(
                        "Team should not be provided for individual registration"
                );
            }

            // Find participant
            Participant participant =
                    participantRepository
                            .findById(request.getParticipantId())
                            .orElseThrow(() ->
                                    new ResourceNotFoundException(
                                            "Participant not found with id: "
                                                    + request.getParticipantId()
                                    ));

            // Check duplicate registration
            boolean alreadyRegistered =
                    registrationRepository
                            .existsByActivityIdAndParticipantId(
                                    request.getActivityId(),
                                    request.getParticipantId()
                            );

            if (alreadyRegistered) {
                throw new RuntimeException(
                        "Participant is already registered for this activity"
                );
            }

            // Create registration
            Registration registration =
                    new Registration();

            registration.setActivity(activity);
            registration.setParticipant(participant);
            registration.setTeam(null);
            registration.setRegistrationType(
                    RegistrationType.INDIVIDUAL
            );
            registration.setRegisteredAt(
                    LocalDateTime.now()
            );
            registration.setStatus(
                    RegistrationStatus.CONFIRMED
            );

            Registration saved =
                    registrationRepository.save(registration);

            return convertToResponse(saved);
        }


        // 3. Team registration
        if (request.getRegistrationType()
                == RegistrationType.TEAM) {

            // Team is required
            if (request.getTeamId() == null) {
                throw new RuntimeException(
                        "Team is required for team registration"
                );
            }

            // Participant should not be provided
            if (request.getParticipantId() != null) {
                throw new RuntimeException(
                        "Participant should not be provided for team registration"
                );
            }

            // Find team
            Team team =
                    teamRepository
                            .findById(request.getTeamId())
                            .orElseThrow(() ->
                                    new ResourceNotFoundException(
                                            "Team not found with id: "
                                                    + request.getTeamId()
                                    ));

            // Check duplicate registration
            boolean alreadyRegistered =
                    registrationRepository
                            .existsByActivityIdAndTeamId(
                                    request.getActivityId(),
                                    request.getTeamId()
                            );

            if (alreadyRegistered) {
                throw new RuntimeException(
                        "Team is already registered for this activity"
                );
            }

            // Create registration
            Registration registration =
                    new Registration();

            registration.setActivity(activity);
            registration.setParticipant(null);
            registration.setTeam(team);
            registration.setRegistrationType(
                    RegistrationType.TEAM
            );
            registration.setRegisteredAt(
                    LocalDateTime.now()
            );
            registration.setStatus(
                    RegistrationStatus.CONFIRMED
            );

            Registration saved =
                    registrationRepository.save(registration);

            return convertToResponse(saved);
        }


        throw new RuntimeException(
                "Invalid registration type"
        );
    }


    // Get all registrations
    public List<RegistrationResponse> getAllRegistrations() {

        return registrationRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }


    // Get registration by ID
    public RegistrationResponse getRegistrationById(
            Integer id) {

        Registration registration =
                registrationRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Registration not found with id: "
                                                + id
                                ));

        return convertToResponse(registration);
    }


    // Get registrations by activity
    public List<RegistrationResponse> getRegistrationsByActivity(
            Integer activityId) {

        return registrationRepository
                .findByActivityId(activityId)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }


    // Delete registration
    public void deleteRegistration(Integer id) {

        if (!registrationRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Registration not found with id: " + id
            );
        }

        registrationRepository.deleteById(id);
    }


    // Entity → Response DTO
    private RegistrationResponse convertToResponse(
            Registration registration) {

        RegistrationResponse response =
                new RegistrationResponse();

        response.setId(registration.getId());

        response.setRegistrationType(
                registration.getRegistrationType()
        );

        response.setRegisteredAt(
                registration.getRegisteredAt()
        );

        response.setStatus(
                registration.getStatus()
        );

        if (registration.getActivity() != null) {
            response.setActivityId(
                    registration.getActivity().getId()
            );
        }

        if (registration.getParticipant() != null) {
            response.setParticipantId(
                    registration.getParticipant().getId()
            );
        }

        if (registration.getTeam() != null) {
            response.setTeamId(
                    registration.getTeam().getId()
            );
        }

        return response;
    }
}
