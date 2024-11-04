package com.libraryManagement.controller;

import com.libraryManagement.customExceptionHandling.SeatServiceException;
import com.libraryManagement.dto.SeatDto;
import com.libraryManagement.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @PostMapping("/create")
    public ResponseEntity<String> createSeat(@RequestBody SeatDto seatDto) {
        try {
            seatService.createSeat(seatDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Seat created successfully.");
        } catch (SeatServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

