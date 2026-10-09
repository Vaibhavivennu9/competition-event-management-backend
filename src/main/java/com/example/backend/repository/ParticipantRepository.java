package com.example.backend.repository;

import com.example.backend.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParticipantRepository extends JpaRepository<Participant, Integer> {

    Optional<Participant> findByEmail(String email);

    boolean existsByEmail(String email);
}