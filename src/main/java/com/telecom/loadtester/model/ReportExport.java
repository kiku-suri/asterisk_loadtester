package com.telecom.loadtester.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "report_export")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportExport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    private String reportName;

    private String reportType;

    private LocalDateTime generatedTime;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private TestJob testJob;

    @ManyToOne
    @JoinColumn(name = "generated_by")
    private User generatedBy;
}
