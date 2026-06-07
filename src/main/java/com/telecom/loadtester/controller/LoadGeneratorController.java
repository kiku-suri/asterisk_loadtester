package com.telecom.loadtester.controller;

import com.telecom.loadtester.service.GeneratorValidationService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.telecom.loadtester.model.LoadGenerator;
import com.telecom.loadtester.service.LoadGeneratorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/generators")
public class LoadGeneratorController {

    private final LoadGeneratorService service;
    private final GeneratorValidationService validationService;

    public LoadGeneratorController(
            LoadGeneratorService service,
	    GeneratorValidationService validationService) {

        this.service = service;
	this.validationService = validationService;
    }

    @GetMapping
    public String list(
            Model model) {

        model.addAttribute(
                "generators",
                service.getAll());

        return "generator/list";
    }

    @GetMapping("/new")
    public String createForm(
            Model model) {

        model.addAttribute(
                "generator",
                new LoadGenerator());

        return "generator/create";
    }

    @PostMapping
    public String save(
            @ModelAttribute LoadGenerator generator) {

        service.save(generator);

        return "redirect:/generators";
    }

    @GetMapping("/delete/{id}")
    public String delete(
            @PathVariable Long id) {

        service.delete(id);

        return "redirect:/generators";
    }

    @GetMapping("/validate/{id}")
    public String validate(
        @PathVariable Long id,
        RedirectAttributes redirect) {

    LoadGenerator generator =
            service.findById(id);

    boolean connected =
            validationService
                    .validateGenerator(generator);

    boolean sippInstalled =
            validationService
                    .verifySippInstalled(generator);

    if (connected && sippInstalled) {

        redirect.addFlashAttribute(
                "success",
                "Generator validation successful");

    } else {

        redirect.addFlashAttribute(
                "error",
                "Validation failed");
    }

    return "redirect:/generators";
    }

}
