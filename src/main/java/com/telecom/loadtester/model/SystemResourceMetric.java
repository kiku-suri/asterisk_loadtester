package com.telecom.loadtester.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "system_resource_metrics")
public class SystemResourceMetric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long campaignId;
    private String serverType;
    private String serverIp;

    private Double cpuUsage;
    private Double memoryUsage;
    private Double diskUsage;
    private Double loadAverage;
    private Integer activeCalls;

    private LocalDateTime collectedAt = LocalDateTime.now();

    public Long getId() { return id; }

    public Long getCampaignId() { return campaignId; }
    public void setCampaignId(Long campaignId) { this.campaignId = campaignId; }

    public String getServerType() { return serverType; }
    public void setServerType(String serverType) { this.serverType = serverType; }

    public String getServerIp() { return serverIp; }
    public void setServerIp(String serverIp) { this.serverIp = serverIp; }

    public Double getCpuUsage() { return cpuUsage; }
    public void setCpuUsage(Double cpuUsage) { this.cpuUsage = cpuUsage; }

    public Double getMemoryUsage() { return memoryUsage; }
    public void setMemoryUsage(Double memoryUsage) { this.memoryUsage = memoryUsage; }

    public Double getDiskUsage() { return diskUsage; }
    public void setDiskUsage(Double diskUsage) { this.diskUsage = diskUsage; }

    public Double getLoadAverage() { return loadAverage; }
    public void setLoadAverage(Double loadAverage) { this.loadAverage = loadAverage; }

    public Integer getActiveCalls() { return activeCalls; }
    public void setActiveCalls(Integer activeCalls) { this.activeCalls = activeCalls; }

    public LocalDateTime getCollectedAt() { return collectedAt; }
}
