package com.example.backend.entity;

import com.example.backend.enums.CompetitionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String description;

    private String startDate;

    private String endDate;
    @Enumerated(EnumType.STRING)
     @Column(nullable=false)
    private CompetitionStatus status;

    @OneToMany(mappedBy = "competition")
    private List<Activity> activities;
}
