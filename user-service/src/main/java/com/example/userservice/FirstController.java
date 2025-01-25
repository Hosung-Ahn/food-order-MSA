package com.example.userservice;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("first")
@RequestMapping("/first-service")
public class FirstController {
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the First Service";
    }
}
