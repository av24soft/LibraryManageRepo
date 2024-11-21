package com.libraryManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShutdownController {

    private final ConfigurableApplicationContext context;

    @Autowired
    public ShutdownController(ConfigurableApplicationContext context) {
        this.context = context;
    }

    @DeleteMapping("/shutdown")
    public String shutdownApp() {
        new Thread(() -> {
            try {
                Thread.sleep(1000); // Give the response time to be sent before shutting down
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            context.close(); // Graceful shutdown
        }).start();
        return "Application is shutting down...";
    }
}

