package com.example.backend.dto.competition;

import com.example.backend.enums.CompetitionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompetitionResponse {

    private Integer id;

    private String name;

    private String description;

    private String startDate;

    private String endDate;

    private CompetitionStatus status;
}