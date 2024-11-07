package com.libraryManagement.service;

import com.libraryManagement.dto.BookSeatDto;
import com.libraryManagement.entity.Seat;

public interface BookSeatService {

	public String bookSeat(BookSeatDto bookSeatDto);
	
	public float seatBookingCalculation(Seat seat);

}