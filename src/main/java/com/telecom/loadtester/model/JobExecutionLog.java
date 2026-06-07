package com.telecom.loadtester.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "job_execution_log")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobExecutionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    private String logLevel;

    @Lob
    private String logMessage;

    private LocalDateTime createdTime;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private TestJob testJob;
}
