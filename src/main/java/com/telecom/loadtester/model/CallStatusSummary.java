package com.telecom.loadtester.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "call_status_summary")
public class CallStatusSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long campaignId;

    private Integer totalCalls = 0;
    private Integer successCalls = 0;
    private Integer failedCalls = 0;
    private Integer busyCalls = 0;
    private Integer noAnswerCalls = 0;
    private Integer errorCalls = 0;
    private Double successRate = 0.0;

    private LocalDateTime collectedAt = LocalDateTime.now();

    public Long getId() { return id; }
    public Long getCampaignId() { return campaignId; }
    public void setCampaignId(Long campaignId) { this.campaignId = campaignId; }

    public Integer getTotalCalls() { return totalCalls; }
    public void setTotalCalls(Integer totalCalls) { this.totalCalls = totalCalls; }

    public Integer getSuccessCalls() { return successCalls; }
    public void setSuccessCalls(Integer successCalls) { this.successCalls = successCalls; }

    public Integer getFailedCalls() { return failedCalls; }
    public void setFailedCalls(Integer failedCalls) { this.failedCalls = failedCalls; }

    public Integer getBusyCalls() { return busyCalls; }
    public void setBusyCalls(Integer busyCalls) { this.busyCalls = busyCalls; }

    public Integer getNoAnswerCalls() { return noAnswerCalls; }
    public void setNoAnswerCalls(Integer noAnswerCalls) { this.noAnswerCalls = noAnswerCalls; }

    public Integer getErrorCalls() { return errorCalls; }
    public void setErrorCalls(Integer errorCalls) { this.errorCalls = errorCalls; }

    public Double getSuccessRate() { return successRate; }
    public void setSuccessRate(Double successRate) { this.successRate = successRate; }

    public LocalDateTime getCollectedAt() { return collectedAt; }
}
