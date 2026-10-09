package com.example.backend.dto.participant;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParticipantRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String studentId;

    @NotBlank
    private String institution;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String phone;
}