package com.telecom.loadtester.controller;

import com.telecom.loadtester.service.StatisticsCollectionService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/campaign-statistics")
public class StatisticsResourceController {

    private final StatisticsCollectionService statisticsService;

    public StatisticsResourceController(
            StatisticsCollectionService statisticsService) {

        this.statisticsService = statisticsService;
    }

    @GetMapping("/{campaignId}")
    public String view(
            @PathVariable Long campaignId,
            Model model) {

        model.addAttribute("campaignId", campaignId);
        model.addAttribute("callStatus", statisticsService.getCallStatus(campaignId));
        model.addAttribute("resourceMetrics", statisticsService.getResourceMetrics(campaignId));

        return "statistics/resources";
    }

    @GetMapping("/{campaignId}/collect")
    public String collect(
            @PathVariable Long campaignId) {

        statisticsService.collectCallStatus(campaignId);
        statisticsService.collectDutResources(campaignId);
        statisticsService.collectGeneratorResources(campaignId);

        return "redirect:/campaign-statistics/" + campaignId;
    }
}
