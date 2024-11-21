package com.libraryManagement.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Override
	public Seat createSeat(SeatDto dto) {
		try {
			if (dto.getFees() <= 0) {
				throw new SeatServiceException("Fees must be greater than 0");
			}

			Seat seat = new Seat();
			seat.setFees(dto.getFees());
			seat.setAvailable(true);

			Row row = rowRepository.findById(dto.getRowId())
					.orElseThrow(() -> new SeatServiceException("Invalid Row ID"));
			seat.setRow(row);

			return seatRepository.save(seat);

		} catch (SeatServiceException e) {

			throw new SeatServiceException("Seat creation failed:" + e.getMessage());
		} catch (Exception e) {

			throw new SeatServiceException("An unexpected error occurred: " + e.getMessage());
		}
	}

	@Override
	public List<Seat> getVacantSeats() {
		try {
			List<Seat> vacantSeats = seatRepository.findByIsAvailable(true);

			if (vacantSeats.isEmpty()) {
				throw new SeatServiceException("No vacant seats available.");
			}

			return vacantSeats;
		} catch (Exception e) {
			throw new SeatServiceException("Failed to fetch vacant seats: " + e.getMessage());
		}
	}

	@Override
	public void deleteSeat(int seatNo) {
		try {
			if (seatRepository.existsById(seatNo)) {
				seatRepository.deleteById(seatNo);
				System.out.println("Seat with ID " + seatNo + " deleted successfully.");
			} else {
				throw new SeatServiceException("Seat with ID " + seatNo + " does not exist.");
			}
		} catch (Exception e) {
			throw new SeatServiceException("Failed to delete seat: " + e.getMessage());
		}
	}

	@Override
	public Seat cancelSeat(BookSeatDto dto) {
		try {
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

			return seat;
		} catch (SeatServiceException e) {

			throw new SeatServiceException("invalid booking id" + e.getMessage());
		} catch (Exception e) {

			throw new SeatServiceException("Seat Cancelation failed  " + e.getMessage());
		}

	}
}
