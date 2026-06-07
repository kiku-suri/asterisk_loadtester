package com.telecom.loadtester.controller;

import com.telecom.loadtester.model.TestConfiguration;
import com.telecom.loadtester.repository.TestConfigurationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TestConfigurationController {

    private final TestConfigurationRepository repository;

    public TestConfigurationController(
            TestConfigurationRepository repository) {

        this.repository = repository;
    }

    @GetMapping("/test-configs")
    public String list(
            Model model) {

        model.addAttribute(
                "configs",
                repository.findAll());

        return "testconfig/list";
    }

    @GetMapping("/test-configs/new")
    public String createForm(
            Model model) {

        model.addAttribute(
                "config",
                new TestConfiguration());

        return "testconfig/create";
    }

    @PostMapping("/test-configs")
    public String save(
            @ModelAttribute TestConfiguration config) {

        repository.save(config);

        return "redirect:/test-configs";
    }
}
