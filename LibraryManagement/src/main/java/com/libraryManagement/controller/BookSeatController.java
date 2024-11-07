
package com.libraryManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libraryManagement.dto.BookSeatDto;
import com.libraryManagement.entity.Seat;
import com.libraryManagement.service.BookSeatService;

@RestController
@RequestMapping("/book")
public class BookSeatController {

	@Autowired
	BookSeatService bookSeatService;

	@PostMapping
	public ResponseEntity<Seat> bookSeat(@RequestBody BookSeatDto bookSeatDto) {
		String s = bookSeatService.bookSeat(bookSeatDto);

		return new ResponseEntity(s,HttpStatus.OK);

	}

}