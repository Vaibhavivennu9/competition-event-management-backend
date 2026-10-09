package com.example.backend.dto.registration;

import com.example.backend.enums.RegistrationType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {

    @NotNull
    private Integer activityId;

    private Integer participantId;

    private Integer teamId;

    @NotNull
    private RegistrationType registrationType;
}
