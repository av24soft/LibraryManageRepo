package com.libraryManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shutdown")
public class ApplicationController {

    @Autowired
    private ApplicationContext context;

    @PostMapping
    public String shutdown() {
        // Gracefully shuts down the application with default exit code (0)
        SpringApplication.exit(context, () -> 0);  // Exit code 0 for normal shutdown
        return "Application is shutting down.";
    }
}
