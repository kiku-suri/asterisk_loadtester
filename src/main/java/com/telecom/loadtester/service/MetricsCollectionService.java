package com.telecom.loadtester.service;

import com.telecom.loadtester.model.*;
import com.telecom.loadtester.repository.SystemMetricsRepository;

import org.springframework.stereotype.Service;

@Service
public class MetricsCollectionService {

    private final SshExecutionService sshService;
    private final SystemMetricsRepository repository;

    public MetricsCollectionService(
            SshExecutionService sshService,
            SystemMetricsRepository repository) {

        this.sshService = sshService;
        this.repository = repository;
    }

    public void collectMetrics(
            TestCampaign campaign) {

        DutServer dut =
                campaign.getDutServer();

        String cpu =
                sshService.executeCommand(
                        dut.getIpAddress(),
                        dut.getSshPort(),
                        dut.getSshUsername(),
                        dut.getSshPassword(),
                        "top -bn1 | grep Cpu");

        String memory =
                sshService.executeCommand(
                        dut.getIpAddress(),
                        dut.getSshPort(),
                        dut.getSshUsername(),
                        dut.getSshPassword(),
                        "free -m | grep Mem");

        String load =
                sshService.executeCommand(
                        dut.getIpAddress(),
                        dut.getSshPort(),
                        dut.getSshUsername(),
                        dut.getSshPassword(),
                        "cat /proc/loadavg");

        SystemMetrics metrics =
                new SystemMetrics();

        metrics.setCampaignId(
                campaign.getCampaignId());

        metrics.setCpuUsage(
                parseCpu(cpu));

        metrics.setMemoryUsage(
                parseMemory(memory));

        metrics.setLoadAverage(
                parseLoad(load));

        repository.save(metrics);
    }

    private Double parseCpu(String value) {
        return 0.0;
    }

    private Double parseMemory(String value) {
        return 0.0;
    }

    private Double parseLoad(String value) {
        return 0.0;
    }
}
