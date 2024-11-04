package com.libraryManagement.serviceImpl;
import com.libraryManagement.configuration.EmailService;
import com.libraryManagement.entity.Seat;
import com.libraryManagement.repository.SeatRepository;
import com.libraryManagement.service.NotificationService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private  SeatRepository seatRepository;
    @Autowired
    private  EmailService emailService;

    @Scheduled(cron = "0 0 0 * * ?") // Runs daily at midnight
    public void notifyExpiringSeats() throws MessagingException, IOException {
        LocalDate today = LocalDate.now();
        LocalDate notificationDate = today.plusDays(3); // Notify 3 days before end date

        List<Seat> expiringSeats = seatRepository.findByEndDate(notificationDate);

        for (Seat seat : expiringSeats) {
            if (seat.getUserDetails() != null) {
                String userEmail = seat.getUserDetails().getUserEmail();
                String userName = seat.getUserDetails().getUserName();
                emailService.sendRenewalReminderWithInvoice(userEmail, userName, seat);
            }
        }
    }
}

