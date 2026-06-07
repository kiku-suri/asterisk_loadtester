package com.telecom.loadtester.controller;

import com.telecom.loadtester.model.DutServer;
import com.telecom.loadtester.repository.DutServerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class DutController {

    private final DutServerRepository repository;

    @GetMapping("/duts")
    public String list(Model model) {

        model.addAttribute(
                "duts",
                repository.findAll());

        return "dut/list";
    }

    @GetMapping("/duts/new")
    public String createForm(Model model) {

        model.addAttribute(
                "dut",
                new DutServer());

        return "dut/create";
    }

    @PostMapping("/duts")
    public String save(
            @ModelAttribute DutServer dut) {

        repository.save(dut);

        return "redirect:/duts";
    }
}
