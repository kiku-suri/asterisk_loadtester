package com.telecom.loadtester.controller;

import com.telecom.loadtester.service.AuthenticationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/")
    public String root() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/authenticate")
    public String authenticate(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        boolean authenticated =
                authenticationService.authenticate(
                        username,
                        password);

        if (!authenticated) {

            model.addAttribute(
                    "error",
                    "Invalid Username or Password");

            return "login";
        }

        session.setAttribute(
                "loggedInUser",
                username);

        return "redirect:/dashboard";
    }

    @GetMapping("/logout")
    public String logout(
            HttpSession session) {

        session.invalidate();

        return "redirect:/login";
    }


}
