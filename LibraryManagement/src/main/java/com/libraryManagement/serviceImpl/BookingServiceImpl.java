package com.libraryManagement.serviceImpl;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryManagement.customExceptionHandling.AlreadyBookedException;
import com.libraryManagement.customExceptionHandling.SeatNotFoundException;
import com.libraryManagement.customExceptionHandling.UserDoesNotExists;
import com.libraryManagement.dto.BookSeatDto;
import com.libraryManagement.entity.Seat;
import com.libraryManagement.entity.UserDetails;
import com.libraryManagement.repository.SeatRepository;
import com.libraryManagement.repository.UserRepository;
import com.libraryManagement.service.BookingService;
@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SeatRepository seatRepository;

    @Override
    public String bookSeat(BookSeatDto bookSeatDto) {

        Optional<UserDetails> users = userRepository.findById(bookSeatDto.getUserid());
        if (users.isEmpty()) {
            throw new UserDoesNotExists(404,"User does not exist.");
        } 

        UserDetails user = users.get();

        Optional<Seat> seats = seatRepository.findById(bookSeatDto.getSeatNo());
        if (seats.isEmpty()) {
            throw new SeatNotFoundException(404,"Seat does not exist.");
        }  

        Seat seat = seats.get();

        if (seat.getUserDetails() != null) {
            throw new AlreadyBookedException(409,"Seat is already booked.");
        }


        seat.setUserDetails(user);
        
        seatRepository.save(seat);

        return "Seat booked successfully";
    }

}

