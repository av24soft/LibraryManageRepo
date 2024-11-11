package com.libraryManagement.service;


import jakarta.mail.MessagingException;

import java.io.IOException;

public interface NotificationService {
    void notifyExpiringSeats() throws MessagingException, IOException;
}


