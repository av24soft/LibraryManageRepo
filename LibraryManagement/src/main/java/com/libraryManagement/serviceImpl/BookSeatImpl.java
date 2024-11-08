package com.libraryManagement.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryManagement.customExceptionHandling.BookSeatException;
import com.libraryManagement.dto.BookSeatDto;
import com.libraryManagement.entity.Seat;
import com.libraryManagement.entity.UserDetails;
import com.libraryManagement.repository.SeatRepository;
import com.libraryManagement.repository.UserRepository;
import com.libraryManagement.service.BookSeatService;

@Service
public class BookSeatImpl implements BookSeatService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	SeatRepository seatRepository;

	@Override
	public String bookSeat(BookSeatDto bookSeatDto) {

		Optional<UserDetails> users = userRepository.findById(bookSeatDto.getUserid());
		if (users.isEmpty()) {
			throw new BookSeatException(404, "User does not exist.");
		}

		UserDetails user = users.get();

		Optional<Seat> seats = seatRepository.findById(bookSeatDto.getSeatNo());
		if (seats.isEmpty()) {
			throw new BookSeatException(404, "Seat does not exist.");
		}

		Seat seat = seats.get();

		if (seat.getUserDetails() != null) {
			throw new BookSeatException(409, "Seat is already booked.");
		}

		seat.setUserDetails(user);
		seat.setStartDate(bookSeatDto.getStartDate());
		seat.setEndDate(bookSeatDto.getEndDate());
		seat.setBookedStatus(bookSeatDto.getBookedStatus());

		seatRepository.save(seat);

		return "Seat booked successfully";
	}

}
