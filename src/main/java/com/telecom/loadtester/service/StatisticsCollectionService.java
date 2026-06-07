package com.telecom.loadtester.service;

import com.telecom.loadtester.model.*;
import com.telecom.loadtester.repository.*;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsCollectionService {

    private final CallStatusSummaryRepository callStatusRepository;
    private final SystemResourceMetricRepository resourceRepository;
    private final TestCampaignService campaignService;
    private final SshExecutionService sshService;

    public StatisticsCollectionService(
            CallStatusSummaryRepository callStatusRepository,
            SystemResourceMetricRepository resourceRepository,
            TestCampaignService campaignService,
            SshExecutionService sshService) {

        this.callStatusRepository = callStatusRepository;
        this.resourceRepository = resourceRepository;
        this.campaignService = campaignService;
        this.sshService = sshService;
    }

    public CallStatusSummary collectCallStatus(Long campaignId) {

        TestCampaign campaign = campaignService.findById(campaignId);

        String output = sshService.executeCommand(
                campaign.getDutServer().getIpAddress(),
                campaign.getDutServer().getSshPort(),
                campaign.getDutServer().getSshUsername(),
                campaign.getDutServer().getSshPassword(),
                "asterisk -rx \"core show channels count\""
        );

        CallStatusSummary summary = new CallStatusSummary();
        summary.setCampaignId(campaignId);

        int activeCalls = extractFirstNumber(output);

        summary.setTotalCalls(activeCalls);
        summary.setSuccessCalls(0);
        summary.setFailedCalls(0);
        summary.setBusyCalls(0);
        summary.setNoAnswerCalls(0);
        summary.setErrorCalls(0);
        summary.setSuccessRate(0.0);

        return callStatusRepository.save(summary);
    }

    public SystemResourceMetric collectDutResources(Long campaignId) {

        TestCampaign campaign = campaignService.findById(campaignId);
        DutServer dut = campaign.getDutServer();

        String command =
                "CPU=$(top -bn1 | grep 'Cpu' | awk '{print 100-$8}'); " +
                "MEM=$(free | awk '/Mem/ {printf(\"%.2f\", $3/$2 * 100.0)}'); " +
                "DISK=$(df / | awk 'NR==2 {gsub(\"%\", \"\", $5); print $5}'); " +
                "LOAD=$(cat /proc/loadavg | awk '{print $1}'); " +
                "CALLS=$(asterisk -rx \"core show channels count\" | grep -o '[0-9]* active' | awk '{print $1}'); " +
                "echo \"$CPU,$MEM,$DISK,$LOAD,$CALLS\"";

        String output = sshService.executeCommand(
                dut.getIpAddress(),
                dut.getSshPort(),
                dut.getSshUsername(),
                dut.getSshPassword(),
                command
        );

        SystemResourceMetric metric = parseMetricLine(
                campaignId,
                "DUT",
                dut.getIpAddress(),
                output
        );

        return resourceRepository.save(metric);
    }

    public SystemResourceMetric collectGeneratorResources(Long campaignId) {

        TestCampaign campaign = campaignService.findById(campaignId);
        LoadGenerator generator = campaign.getLoadGenerator();

        String command =
                "CPU=$(top -bn1 | grep 'Cpu' | awk '{print 100-$8}'); " +
                "MEM=$(free | awk '/Mem/ {printf(\"%.2f\", $3/$2 * 100.0)}'); " +
                "DISK=$(df / | awk 'NR==2 {gsub(\"%\", \"\", $5); print $5}'); " +
                "LOAD=$(cat /proc/loadavg | awk '{print $1}'); " +
                "CALLS=$(pgrep -fc sipp); " +
                "echo \"$CPU,$MEM,$DISK,$LOAD,$CALLS\"";

        String output = sshService.executeCommand(
                generator.getIpAddress(),
                generator.getSshPort(),
                generator.getSshUsername(),
                generator.getSshPassword(),
                command
        );

        SystemResourceMetric metric = parseMetricLine(
                campaignId,
                "LOAD_GENERATOR",
                generator.getIpAddress(),
                output
        );

        return resourceRepository.save(metric);
    }

    public List<CallStatusSummary> getCallStatus(Long campaignId) {
        return callStatusRepository.findByCampaignIdOrderByCollectedAtDesc(campaignId);
    }

    public List<SystemResourceMetric> getResourceMetrics(Long campaignId) {
        return resourceRepository.findByCampaignIdOrderByCollectedAtDesc(campaignId);
    }

    private SystemResourceMetric parseMetricLine(
            Long campaignId,
            String serverType,
            String serverIp,
            String output) {

        SystemResourceMetric metric = new SystemResourceMetric();

        metric.setCampaignId(campaignId);
        metric.setServerType(serverType);
        metric.setServerIp(serverIp);

        try {
            String[] parts = output.trim().split(",");

            metric.setCpuUsage(Double.parseDouble(parts[0].trim()));
            metric.setMemoryUsage(Double.parseDouble(parts[1].trim()));
            metric.setDiskUsage(Double.parseDouble(parts[2].trim()));
            metric.setLoadAverage(Double.parseDouble(parts[3].trim()));
            metric.setActiveCalls(Integer.parseInt(parts[4].trim()));

        } catch (Exception ex) {
            metric.setCpuUsage(0.0);
            metric.setMemoryUsage(0.0);
            metric.setDiskUsage(0.0);
            metric.setLoadAverage(0.0);
            metric.setActiveCalls(0);
        }

        return metric;
    }

    private int extractFirstNumber(String input) {

        String digits = input.replaceAll("[^0-9]", " ").trim();

        if (digits.isEmpty()) {
            return 0;
        }

        return Integer.parseInt(digits.split("\\s+")[0]);
    }
}
