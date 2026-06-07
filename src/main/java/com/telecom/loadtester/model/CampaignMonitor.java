package com.telecom.loadtester.model;

public class CampaignMonitor {

    private String status;

    private String pid;

    private Integer activeCalls;

    private Integer successfulCalls;

    private Integer failedCalls;

    private Double cps;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Integer getActiveCalls() {
        return activeCalls;
    }

    public void setActiveCalls(Integer activeCalls) {
        this.activeCalls = activeCalls;
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
}
