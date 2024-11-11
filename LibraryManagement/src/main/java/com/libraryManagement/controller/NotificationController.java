package com.libraryManagement.controller;

import com.libraryManagement.service.NotificationService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notify-expiring-seats")
    public ResponseEntity<String> notifyExpiringSeats() {
        try {
            notificationService.notifyExpiringSeats();
            return ResponseEntity.ok("Email notifications sent for expiring seats.");
        } catch (MessagingException | IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send email notifications due to a messaging error.");
        }
    }
}


