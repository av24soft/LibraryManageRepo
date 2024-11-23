package com.libraryManagement.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryManagement.customExceptionHandling.BookSeatException;
import com.libraryManagement.dto.BookSeatDto;
import com.libraryManagement.entity.Seat;
import com.libraryManagement.entity.Booking;
import com.libraryManagement.repository.BookingRepository;
import com.libraryManagement.repository.SeatRepository;
import com.libraryManagement.service.BookSeatService;

@Service
public class BookSeatImpl implements BookSeatService {

    private static final Logger logger = LoggerFactory.getLogger(BookSeatImpl.class); // Logger initialization

    @Autowired
    SeatRepository seatRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Override
    public Booking bookSeat(BookSeatDto bookSeatDto) {
        logger.info("Attempting to book seat for booking ID: {}", bookSeatDto.getBookingId());

        try {
            Booking booking = bookingRepository.findById(bookSeatDto.getBookingId())
                    .orElseThrow(() -> new BookSeatException("Invalid Booking ID"));

            if (booking == null) {
                logger.error("Booking with ID {} is not found", bookSeatDto.getBookingId());
                throw new BookSeatException(404, "Invalid id");
            }

            Seat seat = booking.getSeat();

            if (seat == null) {
                logger.error("No seat assigned for booking ID: {}", bookSeatDto.getBookingId());
                throw new BookSeatException(404, "Please select seat first");
            }

            booking.setBookingStatus("Completed");
            seat.setAvailable(false);
            booking.setSeat(seat);

            bookingRepository.save(booking);
            logger.info("Seat booked successfully for booking ID: {}", bookSeatDto.getBookingId());

            return booking;

        } catch (BookSeatException e) {
            logger.error("BookSeatException occurred: {}", e.getMessage());
            throw new BookSeatException(400, "Invalid booking Id");
        } catch (Exception e) {
            logger.error("Exception occurred while booking seat: {}", e.getMessage());
            throw new BookSeatException(400, e.getMessage());
        }
    }
}
