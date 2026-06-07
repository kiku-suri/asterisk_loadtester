package com.telecom.loadtester.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "active_call_tracker")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActiveCallTracker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trackerId;

    private String callId;

    private String caller;

    private String callee;

    private String callStatus;

    private LocalDateTime createdTime;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private TestJob testJob;
}
