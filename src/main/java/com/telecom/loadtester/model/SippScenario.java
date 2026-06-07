package com.telecom.loadtester.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sipp_scenario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SippScenario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scenarioId;

    private String scenarioName;

    private String scenarioFile;

    private String description;

    private Boolean active;
}
