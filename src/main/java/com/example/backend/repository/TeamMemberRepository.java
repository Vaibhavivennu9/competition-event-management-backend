package com.example.backend.repository;

import com.example.backend.entity.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Integer> {

    List<TeamMember> findByTeamId(Integer teamId);

    boolean existsByTeamIdAndParticipantId(
            Integer teamId,
            Integer participantId
    );
}