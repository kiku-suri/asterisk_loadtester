package com.telecom.loadtester.controller;

import com.telecom.loadtester.model.TestCampaign;
import com.telecom.loadtester.repository.DutServerRepository;
import com.telecom.loadtester.repository.LoadGeneratorRepository;
import com.telecom.loadtester.repository.TestConfigurationRepository;
import com.telecom.loadtester.service.CampaignStatisticsService;
import com.telecom.loadtester.service.SippStatisticsParserService;
import com.telecom.loadtester.repository.ScenarioRepository;

import com.telecom.loadtester.service.TestCampaignService;
import com.telecom.loadtester.service.SippExecutionService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/campaigns")
public class TestCampaignController {

    private final TestCampaignService service;
    private final DutServerRepository dutRepository;
    private final LoadGeneratorRepository generatorRepository;
    private final TestConfigurationRepository configRepository;
    private final SippExecutionService sippService;
    private final CampaignStatisticsService statisticsService;
    private final SippStatisticsParserService parserService;
    private final ScenarioRepository scenarioRepository;

    public TestCampaignController(
            TestCampaignService service,
            DutServerRepository dutRepository,
	    SippExecutionService sippService,
	    ScenarioRepository scenarioRepository,
	    SippStatisticsParserService parserService,
            LoadGeneratorRepository generatorRepository,
	    CampaignStatisticsService statisticsService,
            TestConfigurationRepository configRepository) {

        this.service = service;
        this.dutRepository = dutRepository;
	this.sippService = sippService;
	this.parserService = parserService;
	this.scenarioRepository = scenarioRepository;
        this.generatorRepository = generatorRepository;
        this.configRepository = configRepository;
	this.statisticsService = statisticsService;
    }

    @GetMapping
    public String list(
            Model model) {

        model.addAttribute(
                "campaigns",
                service.findAll());

        return "campaign/list";
    }

    @GetMapping("/new")
    public String create(
            Model model) {

        model.addAttribute(
                "campaign",
                new TestCampaign());

        model.addAttribute(
                "duts",
                dutRepository.findAll());

        model.addAttribute(
                "generators",
                generatorRepository.findAll());

        model.addAttribute(
                "configs",
                configRepository.findAll());

	model.addAttribute(
		"scenarios", 
		scenarioRepository.findAll());

        return "campaign/create";
    }

    @PostMapping
    public String save(
            @ModelAttribute TestCampaign campaign) {

        campaign.setStatus("CREATED");

        service.save(campaign);

        return "redirect:/campaigns";
    }

/*    @GetMapping("/start/{id}")
    public String start(
        @PathVariable Long id) {

    	TestCampaign campaign =
            service.get(id);

    	sippService.startCampaign(
            campaign);

    	campaign.setStatus(
            "RUNNING");

    	service.save(campaign);

    	return "redirect:/campaigns";
    }
*/

    @GetMapping("/start/{id}")
    public String startCampaign(
        @PathVariable Long id) {

    TestCampaign campaign =
            service.findById(id);

    String pid =
            sippService.startCampaign(
                    campaign);

    campaign.setSippPid(pid);

    campaign.setStatus(
            "RUNNING");

    campaign.setStartTime(
            LocalDateTime.now());

    service.save(campaign);

    return "redirect:/campaigns";
    }


    @GetMapping("/stop/{id}")
    public String stop(
        @PathVariable Long id) {

    	TestCampaign campaign =
            service.findById(id);

    	sippService.stopCampaign(
            campaign);

    	campaign.setStatus(
            "STOPPED");

	campaign.setEndTime(
            LocalDateTime.now());
        
	String csvContent =
            "200\n200\n200\n500\n200\n";

        statisticsService.saveStatistics(
            campaign,
            csvContent);

        parserService.parseStatistics(
            campaign,
            csvContent);

    	service.save(campaign);

    	return "redirect:/campaigns";
    }    
}
