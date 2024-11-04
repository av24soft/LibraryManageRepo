package com.libraryManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libraryManagement.dto.BookSeatDto;
import com.libraryManagement.service.BookingService;

@RestController
@RequestMapping("/book")
public class BookingController {
	
	@Autowired
	BookingService bookingService;
	
	
	@PostMapping
	public ResponseEntity<BookSeatDto> bookSeat(@RequestBody BookSeatDto  bookSeatDto){
		bookingService.bookSeat(bookSeatDto);
		
		return new ResponseEntity<BookSeatDto>(HttpStatus.OK) ;
		
	}
	
	

}
