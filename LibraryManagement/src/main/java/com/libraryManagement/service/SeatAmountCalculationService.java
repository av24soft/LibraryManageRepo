package com.libraryManagement.service;

import com.libraryManagement.dto.CalculationDto;
import com.libraryManagement.entity.Booking;

public interface SeatAmountCalculationService {
	
	public Booking calculateTotalAmount(CalculationDto calculationDto);

}
