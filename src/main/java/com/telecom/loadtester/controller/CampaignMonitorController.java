package com.telecom.loadtester.controller;

import com.telecom.loadtester.model.TestCampaign;
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

    public CampaignMonitorController(
            TestCampaignService campaignService,
            CampaignMonitoringService monitorService) {

        this.campaignService = campaignService;
        this.monitorService = monitorService;
    }

    @GetMapping
    public String list(Model model) {

        model.addAttribute(
                "campaigns",
                campaignService.findAll());

        return "monitor/list";
    }

    @GetMapping("/{id}")
    public String monitor(
            @PathVariable Long id,
            Model model) {

        TestCampaign campaign =
                campaignService.findById(id);

        model.addAttribute(
                "campaign", campaign);

        model.addAttribute(
                "monitor",
                monitorService.getMonitorData(campaign));

        return "monitor/dashboard";
    }
}
