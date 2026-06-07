package com.telecom.loadtester.controller;

import com.telecom.loadtester.repository.CallRecordRepository;
import com.telecom.loadtester.repository.CampaignStatisticsRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {

    private final CampaignStatisticsRepository statsRepository;
    private final CallRecordRepository callRepository;

    public StatisticsController(
            CampaignStatisticsRepository statsRepository,
            CallRecordRepository callRepository) {

        this.statsRepository = statsRepository;
        this.callRepository = callRepository;
    }

    @GetMapping("/{campaignId}")
    public String dashboard(
            @PathVariable Long campaignId,
            Model model) {

        model.addAttribute(
                "stats",
                statsRepository.findAll());

        model.addAttribute(
                "calls",
                callRepository.findByCampaignId(
                        campaignId));

        return "statistics/dashboard";
    }
}
