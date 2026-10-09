package com.example.backend.repository;

import com.example.backend.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Integer> {

    List<Registration> findByActivityId(Integer activityId);

    List<Registration> findByParticipantId(Integer participantId);

    List<Registration> findByTeamId(Integer teamId);

    boolean existsByActivityIdAndParticipantId(
            Integer activityId,
            Integer participantId
    );

    boolean existsByActivityIdAndTeamId(
            Integer activityId,
            Integer teamId
    );
}
