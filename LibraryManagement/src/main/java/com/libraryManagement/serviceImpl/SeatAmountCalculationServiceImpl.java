package com.libraryManagement.serviceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryManagement.customExceptionHandling.BookSeatException;
import com.libraryManagement.dto.CalculationDto;
import com.libraryManagement.entity.Booking;
import com.libraryManagement.entity.Seat;
import com.libraryManagement.entity.UserDetails;
import com.libraryManagement.repository.BookingRepository;
import com.libraryManagement.repository.SeatRepository;
import com.libraryManagement.repository.UserRepository;
import com.libraryManagement.service.SeatAmountCalculationService;

@Service
public class SeatAmountCalculationServiceImpl implements SeatAmountCalculationService {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private SeatRepository seatRepository;

	@Autowired
	private UserRepository userRepository;

	private static final Logger logger = LoggerFactory.getLogger(SeatAmountCalculationServiceImpl.class);

	public Booking calculateTotalAmount(CalculationDto calculationDto) {

		logger.info("Calculating total amount for user ID: {} and seat number: {}", calculationDto.getUserid(),
				calculationDto.getSeatNo());
		try {
			UserDetails user = userRepository.findById(calculationDto.getUserid())
					.orElseThrow(() -> new BookSeatException(404, "User does not exist."));
			logger.error("User does not exist with ID: {}", calculationDto.getUserid());

			Seat seat = seatRepository.findById(calculationDto.getSeatNo())
					.orElseThrow(() -> new BookSeatException(404, "Seat does not exist."));
			logger.error("Seat does not exist with seat number: {}", calculationDto.getSeatNo());

			if (!seat.isAvailable()) {
				logger.error("Seat {} is already booked.", calculationDto.getSeatNo());
				throw new BookSeatException(409, "Seat is already booked.");
			}

			if (seat.getUserDetails() != null) {
				logger.error("Seat {} is under booking by another user.", calculationDto.getSeatNo());
				throw new BookSeatException(409, "Currently this Seat is under the booking by someone");
			}

			float seatPrice = seat.getFees();

			final int depositAmount = 500;

			Booking booking = new Booking();

			booking.setSeat(seat);
			booking.setUser(user);
			booking.setBookingStatus("Processing...");
			booking.setStartDate(calculationDto.getStartDate());
			booking.setEndDate(calculationDto.getEndDate());
			booking.setBookingTime(LocalDateTime.now());

			LocalDate d1 = booking.getStartDate();
			LocalDate d2 = booking.getEndDate();

			long noOfDays = ChronoUnit.DAYS.between(d1, d2);

			if (noOfDays <= 0) {
                logger.error("Invalid date range. EndDate must be after the StartDate.");
				throw new BookSeatException(422, "EndDate must be after the StartDate");
			}

			float amount = ((seatPrice * (noOfDays + 1)) / 30);
			int roundValue = (Math.round(amount));

			if (user.getDeposit() == 0) {
	            logger.info("Calculated amount for booking: {}", roundValue);
				int totalAmount = (roundValue + depositAmount);
				booking.setTotalFees(totalAmount);
			} else {
				int totalAmount = (roundValue);
				booking.setTotalFees(totalAmount);
	            logger.info("Booking created with total amount: {}",totalAmount);
			}

			user.setDeposit(depositAmount);
			seat.setUserDetails(user);
            logger.info("Booking Done Successfully");
			return bookingRepository.save(booking);

		} catch (Exception e) {
            logger.error("Error during seat booking: {}", e.getMessage());
			throw new BookSeatException(400, e.getMessage());
		}
	}

}
