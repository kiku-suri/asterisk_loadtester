package com.telecom.loadtester.controller;

import com.telecom.loadtester.model.*;
import com.telecom.loadtester.repository.*;
import com.telecom.loadtester.service.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/scenarios")
public class ScenarioController {

    private final ScenarioRepository repository;
    private final ScenarioStorageService storageService;

    public ScenarioController(
            ScenarioRepository repository,
            ScenarioStorageService storageService) {

        this.repository = repository;
        this.storageService = storageService;
    }

    @GetMapping
    public String list(
            Model model) {

        model.addAttribute(
                "scenarios",
                repository.findAll());

        return "scenario/list";
    }

    @GetMapping("/new")
    public String create(
            Model model) {

        model.addAttribute(
                "scenario",
                new Scenario());

        return "scenario/create";
    }

    @PostMapping
    public String upload(
            @RequestParam("file")
            MultipartFile file,

            @ModelAttribute
            Scenario scenario) throws Exception {

        storageService.saveFile(file);

        scenario.setFileName(
                file.getOriginalFilename());

        scenario.setUploadTime(
                LocalDateTime.now());

        scenario.setActive(true);

        repository.save(scenario);

        return "redirect:/scenarios";
    }
}
