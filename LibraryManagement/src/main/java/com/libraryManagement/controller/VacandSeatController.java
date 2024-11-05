package com.libraryManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libraryManagement.service.VacandSeatService;

@RestController
public class VacandSeatController {

	@Autowired
	VacandSeatService vacandSeatService;
	@GetMapping("/seat/{id}")
	public ResponseEntity getSeat(int id) {
		
		vacandSeatService.showSeat(id);
		return new ResponseEntity(HttpStatus.OK);
	}
	@GetMapping("/room/{id}")
	public ResponseEntity getRoom(int id) {
		vacandSeatService.showRoom(id);
		
		return new ResponseEntity(HttpStatus.OK);
	}
	@GetMapping()
	public ResponseEntity getUser(int id) {
		vacandSeatService.showUser(id);
		return new ResponseEntity(HttpStatus.OK);
	}
}
