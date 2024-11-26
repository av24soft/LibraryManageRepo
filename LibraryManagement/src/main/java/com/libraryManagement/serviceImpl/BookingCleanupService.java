package com.libraryManagement.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.libraryManagement.repository.BookingRepository;
import com.libraryManagement.repository.SeatRepository;
import com.libraryManagement.repository.UserRepository;
import com.libraryManagement.entity.Booking;
import com.libraryManagement.entity.Seat;
import com.libraryManagement.entity.UserDetails;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingCleanupService {

	@Autowired
	private SeatRepository seatRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BookingRepository bookingRepository;

	private static final Logger logger = LoggerFactory.getLogger(BookingCleanupService.class);

	@Scheduled(cron = "0 * * * * *")
	public void cleanUpProcessingBookings() {

		LocalDateTime cutoffTime = LocalDateTime.now().minusMinutes(5);
		logger.info("Starting cleanup of processing bookings older than {} minutes", 5);
		List<Booking> bookingsToDelete = bookingRepository.findProcessingBookingsOlderThan5Minutes(cutoffTime);

		for (Booking booking : bookingsToDelete) {

			Seat seat = booking.getSeat();
			UserDetails user = booking.getUser();

			seat.setAvailable(true);
			seat.setUserDetails(null);
			seatRepository.save(seat);

			user.setSeats(null);
			userRepository.save(user);
			logger.debug("User with ID {} has been updated.", user.getUserid());

			System.out.println("Performing operations on booking with ID: " + booking.getBookingId());
			logger.info("Successfully performed operations on booking with ID: {}", booking.getBookingId());

		}

		bookingRepository.deleteOldProcessingBookings(cutoffTime);
		logger.info("Completed cleanup for processing bookings older than {} minutes", 5);

	}
}
