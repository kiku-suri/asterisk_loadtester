package com.telecom.loadtester.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "test_job")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;

    private String jobName;

    private Integer concurrentCalls;

    private Integer cps;

    private Integer callDuration;

    private Integer testDuration;

    private String jobStatus;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private LocalDateTime createdTime;

    @ManyToOne
    @JoinColumn(name = "dut_id")
    private DutServer dutServer;

    @ManyToOne
    @JoinColumn(name = "load_generator_id")
    private LoadGeneratorServer loadGeneratorServer;

    @ManyToOne
    @JoinColumn(name = "scenario_id")
    private SippScenario scenario;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;
}
