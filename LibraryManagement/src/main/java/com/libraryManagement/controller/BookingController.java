
package com.libraryManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libraryManagement.dto.BookSeatDto;
import com.libraryManagement.dto.CalculationDto;
import com.libraryManagement.entity.Booking;
import com.libraryManagement.service.BookSeatService;
import com.libraryManagement.service.SeatAmountCalculationService;

@RestController
@RequestMapping("/seat")
public class BookingController {

	@Autowired
	private BookSeatService bookSeatService;
	
	@Autowired
	private SeatAmountCalculationService amountCalculationService;

	@PostMapping("/book")
	public ResponseEntity<Booking> bookSeat(@RequestBody BookSeatDto bookSeatDto) {
		
		Booking book  = bookSeatService.bookSeat(bookSeatDto);

		return new ResponseEntity<Booking>(book,HttpStatus.OK);

	}
	
	@PostMapping("/calculateAmount")
	public ResponseEntity<Booking> CalculateSeatBooking(@RequestBody CalculationDto calculationDto) {
		
		Booking b = amountCalculationService.calculateTotalAmount(calculationDto);
		return new ResponseEntity<Booking>(b,HttpStatus.OK);
	}
	

}