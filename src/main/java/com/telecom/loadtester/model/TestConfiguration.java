package com.telecom.loadtester.model;

import jakarta.persistence.*;

@Entity
@Table(name = "test_configurations")
public class TestConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_config_id")
    private Long testConfigId;

    @Column(name = "test_name")
    private String testName;

    @Column(name = "call_count")
    private Integer callCount;

    @Column(name = "cps")
    private Integer cps;

    @Column(name = "duration_seconds")
    private Integer durationSeconds;

    public Long getTestConfigId() {
        return testConfigId;
    }

    public void setTestConfigId(Long testConfigId) {
        this.testConfigId = testConfigId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Integer getCallCount() {
        return callCount;
    }

    public void setCallCount(Integer callCount) {
        this.callCount = callCount;
    }

    public Integer getCps() {
        return cps;
    }

    public void setCps(Integer cps) {
        this.cps = cps;
    }

    public Integer getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(Integer durationSeconds) {
        this.durationSeconds = durationSeconds;
    }
}
