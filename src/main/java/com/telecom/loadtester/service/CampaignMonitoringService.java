package com.telecom.loadtester.service;

import com.telecom.loadtester.model.*;

import org.springframework.stereotype.Service;

@Service
public class CampaignMonitoringService {

    private final SippExecutionService sippService;

    public CampaignMonitoringService(
            SippExecutionService sippService) {

        this.sippService = sippService;
    }

    public CampaignMonitor getMonitorData(
            TestCampaign campaign) {

        CampaignMonitor monitor =
                new CampaignMonitor();

        monitor.setPid(
                campaign.getSippPid());

        monitor.setStatus(
                campaign.getStatus());

        monitor.setActiveCalls(0);
        monitor.setSuccessfulCalls(0);
        monitor.setFailedCalls(0);
        monitor.setCps(0.0);

        return monitor;
    }
}
