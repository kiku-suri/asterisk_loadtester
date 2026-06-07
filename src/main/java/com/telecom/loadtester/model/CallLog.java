package com.telecom.loadtester.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "call_log")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CallLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long callLogId;

    private String caller;

    private String callee;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer durationSec;

    private Integer sipResponse;

    private String callStatus;

    private LocalDateTime createdTime;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private TestJob testJob;
}
