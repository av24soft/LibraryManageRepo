package com.libraryManagement.service;

import java.util.List;

import com.libraryManagement.dto.BookSeatDto;
import com.libraryManagement.dto.SeatDto;
import com.libraryManagement.entity.Seat;

public interface SeatService {
	public Seat createSeat(SeatDto dto);

	public List<Seat> getVacantSeats();

	
	public Seat cancelSeat(BookSeatDto dto);

	public void deleteSeat(int seatNo);


}
