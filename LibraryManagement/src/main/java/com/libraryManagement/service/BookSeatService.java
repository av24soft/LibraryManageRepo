package com.libraryManagement.service;

import com.libraryManagement.dto.BookSeatDto;
import com.libraryManagement.entity.Seat;
import com.libraryManagement.entity.Booking;

public interface BookSeatService {

	public Booking bookSeat(BookSeatDto bookSeatDto);
	

}