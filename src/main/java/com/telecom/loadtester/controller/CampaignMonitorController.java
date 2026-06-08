package com.telecom.loadtester.controller;

import com.telecom.loadtester.model.TestCampaign;
import com.telecom.loadtester.repository.SystemMetricsRepository;
import com.telecom.loadtester.service.CampaignMonitoringService;
import com.telecom.loadtester.service.TestCampaignService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/monitor")
public class CampaignMonitorController {

    private final TestCampaignService campaignService;
    private final CampaignMonitoringService monitorService;
    private final SystemMetricsRepository metricsRepository;

    public CampaignMonitorController(
            TestCampaignService campaignService,
            CampaignMonitoringService monitorService,
            SystemMetricsRepository metricsRepository) {

        this.campaignService = campaignService;
        this.monitorService = monitorService;
        this.metricsRepository = metricsRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("campaigns", campaignService.findAll());
        return "monitor/list";
    }

    @GetMapping("/{id}")
    public String monitor(
            @PathVariable Long id,
            Model model) {

        TestCampaign campaign = campaignService.findById(id);

        model.addAttribute("campaign", campaign);
        model.addAttribute("monitor", monitorService.getMonitorData(campaign));
        model.addAttribute(
                "metrics",
                metricsRepository.findByCampaignIdOrderByCollectedAtDesc(id));

        return "monitor/dashboard";
    }
}
