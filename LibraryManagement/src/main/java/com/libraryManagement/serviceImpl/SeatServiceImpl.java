package com.libraryManagement.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryManagement.customExceptionHandling.SeatServiceException;
import com.libraryManagement.dto.SeatDto;
import com.libraryManagement.entity.Row;
import com.libraryManagement.entity.Seat;
import com.libraryManagement.repository.RowRepository;
import com.libraryManagement.repository.SeatRepository;
import com.libraryManagement.service.SeatService;
@Service
public class SeatServiceImpl  implements SeatService{
	@Autowired
	RowRepository rowRepository;
	@Autowired
	SeatRepository seatRepository;
	
	@Override
    public Seat createSeat(SeatDto dto) {
        
         
//        if (dto.getStartDate() == null || dto.getEndDate() == null) {
//            throw new SeatServiceException("Start and end dates are required");
//        }
//        
        if (dto.getFees() <= 0) {
            throw new SeatServiceException("Fees must be greater than 0");
        }

        Seat seat = new Seat();
        seat.setEndDate(dto.getEndDate());
        seat.setStartDate(dto.getStartDate());
        seat.setFees(dto.getFees());
        seat.setAvailable(dto.isAvailable());
        
        
        Row row = rowRepository.findById(dto.getRowId())
            .orElseThrow(() -> new SeatServiceException("Invalid Row ID"));
        seat.setRow(row);

        /*
        Userss userss = userssRepo.findById(dto.getUserssId()).orElseThrow(() -> 
            new SeatServiceException("Invalid Userss ID"));
        seat.setUserss(userss);
        */

        return seatRepository.save(seat);
    }


}
