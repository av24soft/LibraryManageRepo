package com.libraryManagement.service;

import jakarta.mail.MessagingException;

import java.io.IOException;
import java.util.List;

public interface NotificationService {
    void notifyExpiringSeats() throws MessagingException, IOException;
}

