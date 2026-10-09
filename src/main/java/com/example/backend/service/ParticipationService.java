package com.example.backend.service;

import com.example.backend.dto.participation.ParticipationResponse;
import com.example.backend.entity.Activity;
import com.example.backend.entity.Participation;
import com.example.backend.entity.Registration;
import com.example.backend.enums.ParticipationStatus;
import com.example.backend.exception.AlreadyParticipatedException;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.repository.ActivityRepository;
import com.example.backend.repository.ParticipationRepository;
import com.example.backend.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ParticipationService {

    private final ParticipationRepository participationRepository;
    private final RegistrationRepository registrationRepository;
    private final ActivityRepository activityRepository;


    // Event-day check-in
    public ParticipationResponse checkIn(
            Integer registrationId,
            Integer activityId) {

        // 1. Find registration
        Registration registration =
                registrationRepository.findById(registrationId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Registration not found with id: "
                                                + registrationId
                                ));


        // 2. Find activity
        Activity activity =
                activityRepository.findById(activityId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Activity not found with id: "
                                                + activityId
                                ));


        // 3. Check whether already participated
        if (participationRepository
                .existsByRegistrationId(registrationId)) {

            throw new AlreadyParticipatedException(
                    "This registration has already participated"
            );
        }


        // 4. Create participation
        Participation participation = new Participation();

        participation.setRegistration(registration);
        participation.setActivity(activity);
        participation.setParticipatedAt(LocalDateTime.now());
        participation.setStatus("PARTICIPATED");


        // 5. Save entity
        Participation savedParticipation =
                participationRepository.save(participation);


        // 6. Convert Entity -> DTO
        return convertToResponse(savedParticipation);
    }


    // Get participation using registration ID
    public ParticipationResponse getParticipationByRegistration(
            Integer registrationId) {

        Participation participation =
                participationRepository
                        .findByRegistrationId(registrationId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Participation not found for registration id: "
                                                + registrationId
                                ));

        return convertToResponse(participation);
    }


    // Entity -> DTO
    private ParticipationResponse convertToResponse(
            Participation participation) {

        ParticipationResponse response =
                new ParticipationResponse();

        response.setId(participation.getId());

        response.setRegistrationId(
                participation.getRegistration().getId()
        );

        response.setActivityId(
                participation.getActivity().getId()
        );

        response.setParticipatedAt(
                participation.getParticipatedAt()
        );

        response.setStatus(
                ParticipationStatus.PARTICIPATED
        );

        return response;
    }
}