package com.example.backend.dto.participant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ParticipantResponse {

    private Integer id;

    private String name;

    private String studentId;

    private String institution;

    private String email;

    private String phone;
}
