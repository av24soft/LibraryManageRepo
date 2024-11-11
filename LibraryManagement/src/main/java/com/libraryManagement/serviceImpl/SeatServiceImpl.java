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
	        try {
	            if (dto.getFees() <= 0) {
	                throw new SeatServiceException("Fees must be greater than 0");
	            }

	            Seat seat = new Seat();
	            seat.setFees(dto.getFees());
	            seat.setAvailable(true);
	            
	            Row row = rowRepository.findById(dto.getRowId())
	                .orElseThrow(() -> new SeatServiceException("Invalid Row ID"));
	            seat.setRow(row);

	            return seatRepository.save(seat);

	        } catch (SeatServiceException e) {
	            
	      
	            throw new SeatServiceException ("Seat creation failed:" + e.getMessage());  
	        } catch (Exception e) {
	            
	        
	            throw new SeatServiceException("An unexpected error occurred: " + e.getMessage()); 
	        }
	    }

	
}
