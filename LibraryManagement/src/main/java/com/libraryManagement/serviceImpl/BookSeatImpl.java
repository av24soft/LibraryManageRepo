package com.libraryManagement.serviceImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
		
		if(bookSeatDto.getEndDate().isBefore(bookSeatDto.getStartDate())) {
			throw new BookSeatException(422,"EndDate must be after the StartDate");
		}
		
		if(bookSeatDto.getEndDate().equals(bookSeatDto.getStartDate())) {
			throw new BookSeatException(422,"EndDate and StartDate cannot be same");
		}
		

		seat.setUserDetails(user);
		seat.setStartDate(bookSeatDto.getStartDate());
		seat.setEndDate(bookSeatDto.getEndDate());
		seat.setBookedStatus(bookSeatDto.getBookedStatus());
		
		Seat s = seatRepository.save(seat);
		
		seatBookingCalculation(s);
		s.setAvailable(false);
		s.setBookedStatus("pending payment...! ");
		
		seatRepository.save(s);
		
		return "Seat booked successfully "+" payment done "+seatBookingCalculation(s);
	}
	
	

	public float seatBookingCalculation(Seat seat) {

		if(seat != null) {
		
			if(seat.getFees()<=0) {
				throw new BookSeatException(400,"Fees cannot be zero");
			}
			
			if(seat.getEndDate().isBefore(seat.getStartDate())) {
				
				throw new BookSeatException(422,"EndDate must be after the StartDate");
			}
			
		float seatPrice = seat.getFees();
		
		LocalDate d1 = seat.getStartDate();
		LocalDate d2 = seat.getEndDate();
		
		
		long noOfDays = ChronoUnit.DAYS.between(d1, d2);
	    
		if(noOfDays <= 0) {
			throw new BookSeatException(422,"invalid date");
		}
		
	    float totalAmount = (seatPrice * (noOfDays+1))/30;
	    
		return totalAmount;
		}	
		return 0;
	}

}
