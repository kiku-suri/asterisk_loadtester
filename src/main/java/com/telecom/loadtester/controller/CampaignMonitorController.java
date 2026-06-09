package com.telecom.loadtester.controller;

import com.telecom.loadtester.model.TestCampaign;
import com.telecom.loadtester.repository.SystemMetricsRepository;
import com.telecom.loadtester.service.CampaignMonitoringService;
import com.telecom.loadtester.service.TestCampaignService;
import com.telecom.loadtester.service.MetricsCollectionService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/monitor")
public class CampaignMonitorController {

    private final TestCampaignService campaignService;
    private final CampaignMonitoringService monitorService;
    private final SystemMetricsRepository metricsRepository;
    private final MetricsCollectionService metricsService;

    public CampaignMonitorController(
            TestCampaignService campaignService,
            CampaignMonitoringService monitorService,
	    MetricsCollectionService metricsService,
            SystemMetricsRepository metricsRepository) {

        this.campaignService = campaignService;
        this.monitorService = monitorService;
	this.metricsService = metricsService;
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

    @GetMapping("/{id}/collect-metrics")
	public String collectMetrics(
        @PathVariable Long id) {

    	TestCampaign campaign =
            campaignService.findById(id);

    	metricsService.collectMetrics(campaign);

    	return "redirect:/monitor/" + id;
    }
}
