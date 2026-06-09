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

/*    public void collectMetrics(
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
    */


    public void collectMetrics(TestCampaign campaign) {

    try {
        DutServer dut = campaign.getDutServer();

        String command =
                "CPU=$(top -bn1 | grep 'Cpu' | awk '{print 100-$8}'); " +
                "MEM=$(free | awk '/Mem/ {printf(\"%.2f\", $3/$2 * 100.0)}'); " +
                "LOAD=$(cat /proc/loadavg | awk '{print $1}'); " +
                "CALLS=$(asterisk -rx \"core show channels count\" | grep -o '[0-9]* active' | awk '{print $1}'); " +
                "echo \"$CPU,$MEM,$LOAD,$CALLS\"";

        String output = sshService.executeCommand(
                dut.getIpAddress(),
                dut.getSshPort(),
                dut.getSshUsername(),
                dut.getSshPassword(),
                command
        );

        String[] parts = output.trim().split(",");

        SystemMetrics metrics = new SystemMetrics();
        metrics.setCampaignId(campaign.getCampaignId());
        metrics.setCpuUsage(parseDouble(parts, 0));
        metrics.setMemoryUsage(parseDouble(parts, 1));
        metrics.setLoadAverage(parseDouble(parts, 2));
        //metrics.setActiveCalls((int) parseDouble(parts, 3));
	metrics.setActiveCalls(parseDouble(parts, 3).intValue());

        repository.save(metrics);

    } catch (Exception ex) {
        System.out.println("DUT metrics collection failed: " + ex.getMessage());
    }
}

	private Double parseDouble(String[] parts, int index) {

    	try {
        if (parts.length <= index) {
            return 0.0;
        }

        	String value = parts[index].trim();

        	if (value.isEmpty()) {
            	return 0.0;
        	}

	        return Double.parseDouble(value);

    	} catch (Exception ex) {
        	return 0.0;
    	}
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
