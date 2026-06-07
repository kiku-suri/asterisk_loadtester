package com.telecom.loadtester.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "campaign_statistics")
public class CampaignStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statsId;

    private Long campaignId;

    private Integer totalCalls;

    private Integer successfulCalls;

    private Integer failedCalls;

    private Double cps;

    private Double averageResponseMs;

    private LocalDateTime collectedAt =
            LocalDateTime.now();

    public Long getStatsId() {
        return statsId;
    }

    public void setStatsId(Long statsId) {
        this.statsId = statsId;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public Integer getTotalCalls() {
        return totalCalls;
    }

    public void setTotalCalls(Integer totalCalls) {
        this.totalCalls = totalCalls;
    }

    public Integer getSuccessfulCalls() {
        return successfulCalls;
    }

    public void setSuccessfulCalls(Integer successfulCalls) {
        this.successfulCalls = successfulCalls;
    }

    public Integer getFailedCalls() {
        return failedCalls;
    }

    public void setFailedCalls(Integer failedCalls) {
        this.failedCalls = failedCalls;
    }

    public Double getCps() {
        return cps;
    }

    public void setCps(Double cps) {
        this.cps = cps;
    }

    public Double getAverageResponseMs() {
        return averageResponseMs;
    }

    public void setAverageResponseMs(Double averageResponseMs) {
        this.averageResponseMs = averageResponseMs;
    }

    public LocalDateTime getCollectedAt() {
        return collectedAt;
    }

    public void setCollectedAt(LocalDateTime collectedAt) {
        this.collectedAt = collectedAt;
    }
}
