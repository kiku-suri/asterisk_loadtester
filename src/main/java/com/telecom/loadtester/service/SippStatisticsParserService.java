package com.telecom.loadtester.service;

import com.telecom.loadtester.model.CallRecord;
import com.telecom.loadtester.model.CampaignStatistics;
import com.telecom.loadtester.model.TestCampaign;
import com.telecom.loadtester.repository.CallRecordRepository;
import com.telecom.loadtester.repository.CampaignStatisticsRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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

        if (csvContent == null || csvContent.trim().isEmpty()) {
            return;
        }

        String[] lines = csvContent.split("\\R");

        if (lines.length < 2) {
            return;
        }

        String headerLine = lines[0].trim();

        String lastDataLine = null;

        for (int i = 1; i < lines.length; i++) {
            String line = lines[i].trim();

            if (!line.isEmpty()) {
                lastDataLine = line;
            }
        }

        if (lastDataLine == null) {
            return;
        }

        String[] headers = headerLine.split(";");
        String[] values = lastDataLine.split(";");

        Map<String, Integer> indexMap = new HashMap<>();

        for (int i = 0; i < headers.length; i++) {
            indexMap.put(headers[i].trim(), i);
        }

        int totalCalls =
                getInt(values, indexMap, "TotalCallCreated");

        int successfulCalls =
                getInt(values, indexMap, "SuccessfulCall(C)");

        int failedCalls =
                getInt(values, indexMap, "FailedCall(C)");

        double cps =
                getDouble(values, indexMap, "CallRate(C)");

        CampaignStatistics stats =
                new CampaignStatistics();

        stats.setCampaignId(campaign.getCampaignId());
        stats.setTotalCalls(totalCalls);
        stats.setSuccessfulCalls(successfulCalls);
        stats.setFailedCalls(failedCalls);
        stats.setCps(cps);
        stats.setAverageResponseMs(0.0);

        statsRepository.save(stats);

        CallRecord record =
                new CallRecord();

        record.setCampaignId(campaign.getCampaignId());
        record.setResponseCode(
                failedCalls > 0 ? "FAILED" : "200");

        record.setCallResult(
                failedCalls > 0 ? "FAILED" : "SUCCESS");

        callRepository.save(record);
    }

    private int getInt(
            String[] values,
            Map<String, Integer> indexMap,
            String columnName) {

        try {
            Integer index = indexMap.get(columnName);

            if (index == null || index >= values.length) {
                return 0;
            }

            String raw =
                    values[index]
                            .trim()
                            .replace(",", "");

            if (raw.isEmpty()) {
                return 0;
            }

            return Integer.parseInt(raw);

        } catch (Exception ex) {
            return 0;
        }
    }

    private double getDouble(
            String[] values,
            Map<String, Integer> indexMap,
            String columnName) {

        try {
            Integer index = indexMap.get(columnName);

            if (index == null || index >= values.length) {
                return 0.0;
            }

            String raw =
                    values[index]
                            .trim()
                            .replace(",", "");

            if (raw.isEmpty()) {
                return 0.0;
            }

            return Double.parseDouble(raw);

        } catch (Exception ex) {
            return 0.0;
        }
    }
}
