package com.libraryManagement.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.libraryManagement.customExceptionHandling.SeatServiceException;
import com.libraryManagement.dto.BookSeatDto;
import com.libraryManagement.dto.SeatDto;
import com.libraryManagement.entity.Booking;
import com.libraryManagement.entity.Row;
import com.libraryManagement.entity.Seat;
import com.libraryManagement.entity.UserDetails;
import com.libraryManagement.repository.BookingRepository;
import com.libraryManagement.repository.RowRepository;
import com.libraryManagement.repository.SeatRepository;
import com.libraryManagement.repository.UserRepository;
import com.libraryManagement.service.SeatService;

@Service
public class SeatServiceImpl implements SeatService {
	@Autowired
	RowRepository rowRepository;
	@Autowired
	SeatRepository seatRepository;
	@Autowired
	BookingRepository bookingRepository;
	@Autowired
	UserRepository userRepository;

	private static final Logger logger = LoggerFactory.getLogger(SeatServiceImpl.class);

	@Override
	public Seat createSeat(SeatDto dto) {
		try {
			logger.info("Creating seat with row ID: {}", dto.getRowId());
			if (dto.getFees() <= 0) {
				logger.error("Invalid seat fees: {}", dto.getFees());
				throw new SeatServiceException("Fees must be greater than 0");
			}

			Seat seat = new Seat();
			seat.setFees(dto.getFees());
			seat.setAvailable(true);

			Row row = rowRepository.findById(dto.getRowId())
					.orElseThrow(() -> new SeatServiceException("Invalid Row ID"));

			seat.setRow(row);
			logger.info("Seat created with row ID: {}", dto.getRowId());

			return seatRepository.save(seat);

		} catch (SeatServiceException e) {
			logger.error("Seat creation failed: {}", e.getMessage());
			throw new SeatServiceException("Seat creation failed:" + e.getMessage());
		} catch (Exception e) {
			logger.error("Unexpected error during seat creation: {}", e.getMessage());
			throw new SeatServiceException("An unexpected error occurred: " + e.getMessage());
		}
	}

	@Override
	public List<Seat> getVacantSeats() {
		try {
			logger.info("Fetching vacant seats");
			List<Seat> vacantSeats = seatRepository.findByIsAvailable(true);

			if (vacantSeats.isEmpty()) {
				logger.error("No vacant seats available.");
				throw new SeatServiceException("No vacant seats available.");
			}
			logger.info("Found {} vacant seats", vacantSeats.size());
			return vacantSeats;
		} catch (Exception e) {
			logger.error("Failed to fetch vacant seats: {}", e.getMessage());
			throw new SeatServiceException("Failed to fetch vacant seats: " + e.getMessage());
		}
	}

	@Override
	public void deleteSeat(int seatNo) {
		try {
			logger.info("Deleting seat with ID: {}", seatNo);

			Seat seat = seatRepository.findById(seatNo)
					.orElseThrow(() -> new SeatServiceException("Seat with ID is not found"));
			logger.error("Seat with ID {} does not exist.", seatNo);

			if (!seat.isAvailable()) {
				logger.error("Seat is already booked. {}", seatNo);
				throw new SeatServiceException("Seat is already booked.");
			}

			seatRepository.deleteById(seatNo);
			logger.info("Seat with ID {} deleted successfully.", seatNo);

		} catch (SeatServiceException e) {
			logger.error("Failed to delete seat with ID {}: {}", seatNo, e.getMessage());
			throw new SeatServiceException("Seat not deleted " + e.getMessage());
		}
	}

	@Override
	public Seat cancelSeat(BookSeatDto dto) {
		try {

			logger.info("Canceling seat for booking ID: {}", dto.getBookingId());

			Optional<Booking> b = bookingRepository.findById(dto.getBookingId());

			Booking booking = b.get();

			Seat seat = booking.getSeat();
			UserDetails user = booking.getUser();
			seat.setAvailable(true);
			booking.setBookingStatus("Canceled !");

			seat.setUserDetails(null);
			user.setSeats(null);

			userRepository.save(user);
			seatRepository.save(seat);
			bookingRepository.save(booking);
			logger.info("Seat with booking ID {} canceled successfully.", dto.getBookingId());

			return seat;
		} catch (SeatServiceException e) {
			logger.error("Seat cancellation failed: {}", e.getMessage());
			throw new SeatServiceException("invalid booking id" + e.getMessage());
		} catch (Exception e) {
			logger.error("Unexpected error during seat cancellation: {}", e.getMessage());
			throw new SeatServiceException("Seat Cancelation failed  " + e.getMessage());
		}

	}
}
