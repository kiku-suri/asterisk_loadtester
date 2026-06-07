package com.telecom.loadtester.service;

import com.telecom.loadtester.model.CallRecord;
import com.telecom.loadtester.model.CampaignStatistics;
import com.telecom.loadtester.model.TestCampaign;
import com.telecom.loadtester.repository.CallRecordRepository;
import com.telecom.loadtester.repository.CampaignStatisticsRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SippStatisticsParserService {

    private final CampaignStatisticsRepository statsRepository;
    private final CallRecordRepository callRepository;

    public SippStatisticsParserService(
            CampaignStatisticsRepository statsRepository,
            CallRecordRepository callRepository) {

        this.statsRepository = statsRepository;
        this.callRepository = callRepository;
    }

    public void parseStatistics(
            TestCampaign campaign,
            String csvContent) {

        CampaignStatistics stats =
                new CampaignStatistics();

        stats.setCampaignId(
                campaign.getCampaignId());

        int totalCalls = 0;
        int successCalls = 0;
        int failedCalls = 0;

        String[] rows =
                csvContent.split("\n");

        for (String row : rows) {

            if (row.trim().isEmpty()) {
                continue;
            }

            totalCalls++;

            if (row.contains("200")) {
                successCalls++;
            } else {
                failedCalls++;
            }

            CallRecord record =
                    new CallRecord();

            record.setCampaignId(
                    campaign.getCampaignId());

            record.setResponseCode(
                    row.contains("200")
                            ? "200"
                            : "FAILED");

            record.setCallResult(
                    row.contains("200")
                            ? "SUCCESS"
                            : "FAILED");

            callRepository.save(record);
        }

        stats.setTotalCalls(totalCalls);
        stats.setSuccessfulCalls(successCalls);
        stats.setFailedCalls(failedCalls);

        if (totalCalls > 0) {

            stats.setCps(
                    (double) totalCalls);

            stats.setAverageResponseMs(
                    50.0);
        }

        statsRepository.save(stats);
    }

    public List<CallRecord> getCallRecords(
            Long campaignId) {

        return callRepository.findByCampaignId(
                campaignId);
    }
}
