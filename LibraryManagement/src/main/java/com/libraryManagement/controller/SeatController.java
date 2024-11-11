package com.libraryManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libraryManagement.dto.SeatDto;
import com.libraryManagement.entity.Seat;
import com.libraryManagement.serviceImpl.SeatServiceImpl;

@RestController
@RequestMapping("seat")
public class SeatController {
	
	@Autowired
	SeatServiceImpl  seatServiceimpl;
	@PostMapping("/createSeat")
	public ResponseEntity<Seat> createSeat(@RequestBody SeatDto dto) {
		seatServiceimpl.createSeat(dto);
		return new ResponseEntity(HttpStatus.CREATED);
		
	
	}

	
}
