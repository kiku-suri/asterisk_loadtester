package com.telecom.loadtester.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "test_campaigns")
public class TestCampaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campaign_id")
    private Long campaignId;

    @Column(name = "campaign_name")
    private String campaignName;

    @ManyToOne
    @JoinColumn(name = "generator_id")
    private LoadGenerator loadGenerator;

    @ManyToOne
    @JoinColumn(name = "dut_id")
    private DutServer dutServer;

    @ManyToOne
    @JoinColumn(name = "test_config_id")
    private TestConfiguration testConfiguration;

    @ManyToOne
    @JoinColumn(name = "scenario_id")
    private Scenario scenario;

    @Column(name = "calls_per_second")
    private Integer callsPerSecond;

    @Column(name = "total_calls")
    private Integer totalCalls;

    @Column(name = "status")
    private String status;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name="sipp_pid")
    private String sippPid;

    @Column(name="result_file")
    private String resultFile;

    @Column(name="execution_log")
    private String executionLog;

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public LoadGenerator getLoadGenerator() {
        return loadGenerator;
    }

    public void setLoadGenerator(LoadGenerator loadGenerator) {
        this.loadGenerator = loadGenerator;
    }

    public DutServer getDutServer() {
        return dutServer;
    }

    public void setDutServer(DutServer dutServer) {
        this.dutServer = dutServer;
    }

    public TestConfiguration getTestConfiguration() {
        return testConfiguration;
    }

    public void setTestConfiguration(
            TestConfiguration testConfiguration) {

        this.testConfiguration = testConfiguration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(
            LocalDateTime startTime) {

        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(
            LocalDateTime endTime) {

        this.endTime = endTime;
    }

    public Scenario getScenario() {
    return scenario;
    }

    public void setScenario(Scenario scenario) {
    this.scenario = scenario;
    }

    public Integer getCallsPerSecond() {
    return callsPerSecond;
    }

    public void setCallsPerSecond(Integer callsPerSecond) {
    this.callsPerSecond = callsPerSecond;
    }

    public Integer getTotalCalls() {
    return totalCalls;
    } 

    public void setTotalCalls(Integer totalCalls) {
    this.totalCalls = totalCalls;
    }

    public String getSippPid() {
    return sippPid;
    }

    public void setSippPid(String sippPid) {
    this.sippPid = sippPid;
    }

    public String getResultFile() {
    return resultFile;
    }

    public void setResultFile(String resultFile) {
    this.resultFile = resultFile;
    }

    public String getExecutionLog() {
    return executionLog;
    }

    public void setExecutionLog(String executionLog) {
    this.executionLog = executionLog;
    }

    public LocalDateTime getCreatedAt() {
    return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
    }
    

}
