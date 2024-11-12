package com.libraryManagement.service;

import com.libraryManagement.dto.CancelSeatDto;
import com.libraryManagement.dto.SeatDto;
import com.libraryManagement.entity.Seat;

public interface SeatService {
	public Seat createSeat(SeatDto dto);
	public Seat cancelSeat(CancelSeatDto dto);
	


}
