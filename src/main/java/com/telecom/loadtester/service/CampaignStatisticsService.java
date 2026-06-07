package com.telecom.loadtester.service;

import com.telecom.loadtester.model.CampaignStatistics;
import com.telecom.loadtester.model.TestCampaign;
import com.telecom.loadtester.repository.CampaignStatisticsRepository;

import org.springframework.stereotype.Service;

@Service
public class CampaignStatisticsService {

    private final CampaignStatisticsRepository repository;

    public CampaignStatisticsService(
            CampaignStatisticsRepository repository) {

        this.repository = repository;
    }

    public void saveStatistics(
            TestCampaign campaign,
            String rawCsv) {

        CampaignStatistics stats =
                new CampaignStatistics();

        stats.setCampaignId(
                campaign.getCampaignId());

        /*
         * Placeholder parser.
         * We'll replace this in Phase 4C
         * with actual SIPp CSV parsing.
         */

        stats.setTotalCalls(100);
        stats.setSuccessfulCalls(95);
        stats.setFailedCalls(5);
        stats.setCps(10.0);
        stats.setAverageResponseMs(55.0);

        repository.save(stats);
    }
}
