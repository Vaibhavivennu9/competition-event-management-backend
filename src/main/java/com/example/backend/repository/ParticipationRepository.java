package com.example.backend.repository;

import com.example.backend.entity.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParticipationRepository extends JpaRepository<Participation, Integer> {

    boolean existsByRegistrationId(Integer registrationId);

    Optional<Participation> findByRegistrationId(Integer registrationId);
}