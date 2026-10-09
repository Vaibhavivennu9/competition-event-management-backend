package com.example.backend.dto.participation;

import com.example.backend.enums.ParticipationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ParticipationResponse {

    private Integer id;

    private Integer registrationId;

    private Integer activityId;

    private LocalDateTime participatedAt;

    private ParticipationStatus status;
}
