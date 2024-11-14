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
			UserDetails user = userRepository.findById(calculationDto.getUserid())
					.orElseThrow(() -> new BookSeatException(404, "User does not exist."));
			
			Seat seat = seatRepository.findById(calculationDto.getSeatNo())
					.orElseThrow(() -> new BookSeatException(404, "Seat does not exist."));

			if (user.getSeat() != null) {
				throw new BookSeatException(409, "One seat is already booked by this User");
			}
			
			if (seat.getUserDetails() != null) {
				throw new BookSeatException(409, "Currently this Seat is under the booking by someone");
			}

			if (!seat.isAvailable()) {
				throw new BookSeatException(409, "Seat is already booked.");
			}

			float seatPrice = seat.getFees();

			final int depositAmount = 500;


			Booking booking = new Booking();

			booking.setSeat(seat);
			booking.setUser(user);
			booking.setBookingStatus("Processing...");
			booking.setStartDate(calculationDto.getStartDate());
			booking.setEndDate(calculationDto.getEndDate());

			LocalDate d1 = booking.getStartDate();
			LocalDate d2 = booking.getEndDate();

			long noOfDays = ChronoUnit.DAYS.between(d1, d2);

			if (noOfDays <= 0) {
				throw new BookSeatException(422, "EndDate must be after the StartDate");
			}
			
			float amount = ((seatPrice*(noOfDays+1))/30);
			int roundValue = (Math.round(amount));

			int totalAmount = (roundValue+depositAmount);
			
			booking.setTotalFees(totalAmount);
			
			user.setDeposit(depositAmount);         
			seat.setUserDetails(user);
			user.setSeat(seat);
			
			return bookingRepository.save(booking);

		} catch (Exception e) {
			throw new BookSeatException(400, e.getMessage());
		}
	}
}
