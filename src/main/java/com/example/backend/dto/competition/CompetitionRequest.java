package com.example.backend.dto.competition;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompetitionRequest {

    private String name;

    private String description;

    private String startDate;

    private String endDate;
}
