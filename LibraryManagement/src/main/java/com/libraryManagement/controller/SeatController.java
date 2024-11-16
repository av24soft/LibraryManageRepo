package com.libraryManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libraryManagement.dto.BookSeatDto;
import com.libraryManagement.dto.SeatDto;
import com.libraryManagement.entity.Seat;
import com.libraryManagement.serviceImpl.SeatServiceImpl;

@RestController
@RequestMapping("seat")
public class SeatController {

	@Autowired
	SeatServiceImpl seatServiceimpl;

	@PostMapping("/createSeat")
	public ResponseEntity<Seat> createSeat(@RequestBody SeatDto dto) {
		return new ResponseEntity<Seat>(seatServiceimpl.createSeat(dto), HttpStatus.CREATED);

	}

	@GetMapping("/vacantSeats")
	public ResponseEntity<Seat> getVacantSeats() {
		List<Seat> vacantSeats = seatServiceimpl.getVacantSeats();
		return new ResponseEntity(vacantSeats, HttpStatus.OK);
	

	@PostMapping("/cancel")
		public ResponseEntity cancelSeat(@RequestBody BookSeatDto dto) {
			Seat s = seatServiceimpl.cancelSeat(dto);
			return new ResponseEntity(s,HttpStatus.OK);
			
	}

   
	@DeleteMapping("/deleteSeat/{seatNo}")
    public ResponseEntity deleteSeat(@PathVariable("seatNo") int seatNo) {
        seatServiceimpl.deleteSeat(seatNo);
        return new ResponseEntity ("Seat deleted",HttpStatus.OK);
    }

}
