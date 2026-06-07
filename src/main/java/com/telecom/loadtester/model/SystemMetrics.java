package com.telecom.loadtester.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "system_metrics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SystemMetrics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long metricId;

    private Double cpuUsage;

    private Double memoryUsage;

    private Double loadAverage;

    private Integer activeCalls;

    private Integer registeredEndpoints;

    private LocalDateTime collectedTime;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private TestJob testJob;
}
