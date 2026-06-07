package com.telecom.loadtester.controller;

import com.telecom.loadtester.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    private final UserRepository userRepository;

    public UserController(
            UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public String users(
            Model model) {

        model.addAttribute(
                "users",
                userRepository.findAll());

        return "user/list";
    }
}
