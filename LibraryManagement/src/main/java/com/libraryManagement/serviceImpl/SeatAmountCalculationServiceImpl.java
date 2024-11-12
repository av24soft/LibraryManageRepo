package com.libraryManagement.serviceImpl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

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

	public Booking calculateTotalAmount(CalculationDto calculationDto) {

		try {
			Optional<UserDetails> users = userRepository.findById(calculationDto.getUserid());
			if (users.isEmpty()) {
				throw new BookSeatException(404, "User does not exist.");
			}

			UserDetails user = users.get();

			Optional<Seat> seats = seatRepository.findById(calculationDto.getSeatNo());
			if (seats.isEmpty()) {
				throw new BookSeatException(404, "Seat does not exist.");
			}

			Seat seat = seats.get();

			if (seat.getUserDetails() != null) {
				throw new BookSeatException(409, "Seat is already booked.");
			}

			float seatPrice = seat.getFees();
			seat.setUserDetails(user);
			user.setSeat(seat);

			seatRepository.save(seat);
			userRepository.save(user);

			Booking booking = new Booking();

			booking.setSeat(seat);
			booking.setUser(user);

			if (calculationDto.getEndDate().isBefore(calculationDto.getStartDate())) {
				throw new BookSeatException(422, "EndDate must be after the StartDate");
			}

			if (calculationDto.getEndDate().equals(calculationDto.getStartDate())) {
				throw new BookSeatException(422, "EndDate and StartDate cannot be same");
			}

			booking.setBooked(calculationDto.isBooked());
			booking.setStartDate(calculationDto.getStartDate());
			booking.setEndDate(calculationDto.getEndDate());
			booking.setCanceled(calculationDto.isCanceled());

			LocalDate d1 = booking.getStartDate();
			LocalDate d2 = booking.getEndDate();

			long noOfDays = ChronoUnit.DAYS.between(d1, d2);

			if (noOfDays <= 0) {
				throw new BookSeatException(422, "invalid date");
			}

			float totalAmount = (seatPrice * (noOfDays + 1)) / 30;

			booking.setPaymentStatus("pending...");
			booking.setTotalFees(totalAmount);

			return bookingRepository.save(booking);

		} catch (Exception e) {
			throw new BookSeatException(400, e.getMessage());
		}
	}
}
