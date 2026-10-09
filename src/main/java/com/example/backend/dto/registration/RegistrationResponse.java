package com.example.backend.dto.registration;

import com.example.backend.enums.RegistrationStatus;
import com.example.backend.enums.RegistrationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class RegistrationResponse {

    private Integer id;

    private Integer activityId;

    private Integer participantId;

    private Integer teamId;

    private RegistrationType registrationType;

    private LocalDateTime registeredAt;

    private RegistrationStatus status;
}