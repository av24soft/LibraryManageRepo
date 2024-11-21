package com.libraryManagement.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryManagement.customExceptionHandling.BookSeatException;
import com.libraryManagement.dto.BookSeatDto;
import com.libraryManagement.entity.Seat;
import com.libraryManagement.entity.Booking;
import com.libraryManagement.repository.BookingRepository;
import com.libraryManagement.repository.SeatRepository;
import com.libraryManagement.service.BookSeatService;

@Service
public class BookSeatImpl implements BookSeatService {

	@Autowired
	SeatRepository seatRepository;

	@Autowired
	BookingRepository bookingRepository;

	@Override
	public Booking bookSeat(BookSeatDto bookSeatDto) {

		try {
			Booking booking = bookingRepository.findById(bookSeatDto.getBookingId())
					.orElseThrow(() -> new BookSeatException("Invalid Booking ID"));

			if (booking == null) {

				throw new BookSeatException(404, "Invalid id");
			}

			Seat seat = booking.getSeat();

			if (seat == null) {

				throw new BookSeatException(404, "Please select seat first");
			}

			booking.setBookingStatus("Completed");

			seat.setAvailable(false);

			booking.setSeat(seat);

			bookingRepository.save(booking);
			return booking;

		} catch (BookSeatException e) {
			throw new BookSeatException(400, "Invalid booking Id");
		} catch (Exception e) {
			throw new BookSeatException(400, e.getMessage());
		}
	}
}
