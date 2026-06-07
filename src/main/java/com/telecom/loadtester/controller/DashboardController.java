package com.telecom.loadtester.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        model.addAttribute("totalDuts", 0);
        model.addAttribute("totalGenerators", 0);
        model.addAttribute("activeTests", 0);
        model.addAttribute("runningCalls", 0);

        return "dashboard";
    }
}
