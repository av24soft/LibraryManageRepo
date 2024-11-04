package com.libraryManagement.serviceImpl;

import com.libraryManagement.dto.SeatDto;
import com.libraryManagement.entity.Row;
import com.libraryManagement.entity.Seat;
import com.libraryManagement.entity.UserDetails;
import com.libraryManagement.repository.RowRepository;
import com.libraryManagement.repository.SeatRepository;
import com.libraryManagement.repository.UserRepository;
import com.libraryManagement.service.SeatService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private RowRepository rowRepository;

    @Autowired
    private UserRepository userRepository;

    public void createSeat(SeatDto seatDto) {

        Row row = rowRepository.findById(seatDto.getRowId())
                .orElseThrow(() -> new EntityNotFoundException("Row not found with ID: " + seatDto.getRowId()));


        UserDetails userDetails = null;

        Seat seat = new Seat();
        seat.setRow(row);
        seat.setUserDetails(userDetails);
        seat.setStartDate(null);
        seat.setEndDate(null);
        seat.setFees(seatDto.getFees());
        seat.setAvailable(true);


        seatRepository.save(seat);
    }
}
