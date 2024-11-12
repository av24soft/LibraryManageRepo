package com.libraryManagement.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryManagement.customExceptionHandling.SeatServiceException;
import com.libraryManagement.dto.CancelSeatDto;
import com.libraryManagement.dto.SeatDto;
import com.libraryManagement.entity.Booking;
import com.libraryManagement.entity.Row;
import com.libraryManagement.entity.Seat;
import com.libraryManagement.repository.BookingRepository;
import com.libraryManagement.repository.RowRepository;
import com.libraryManagement.repository.SeatRepository;
import com.libraryManagement.service.SeatService;
@Service
public class SeatServiceImpl  implements SeatService{
	@Autowired
	RowRepository rowRepository;
	@Autowired
	SeatRepository seatRepository;
	@Autowired
	BookingRepository bookingRepository;

	
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
	 

	@Override
	public Seat cancelSeat(CancelSeatDto dto) {
		try {
		Optional<Booking> b = bookingRepository.findById(dto.getBookingId());
		
		Booking booking =b.get();
			
	
		Seat seat = booking.getSeat();
		seat.setAvailable(true);
		
		seat.setBookedStatus("cancel");
		booking.setBooked(false);
		booking.setCanceled(true);
		seat.setUserDetails(null);
		
		seatRepository.save(seat);
		bookingRepository.save(booking);
		
		
		return seat;
		}
		catch (SeatServiceException e) {
            
		      
            throw new SeatServiceException ("invalid booking id" + e.getMessage());  
        } catch (Exception e) {
            
        
            throw new SeatServiceException("Seat Cancelation failed  " + e.getMessage()); 
        }
		
	}
		

	
}
