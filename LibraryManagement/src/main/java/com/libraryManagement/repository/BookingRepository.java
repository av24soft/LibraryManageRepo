package com.libraryManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.libraryManagement.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer>{
	
	@Query("SELECT b FROM Booking b WHERE b.seat.seatNo = :seatNo AND b.user.userid = :userid")
    List<Booking> findBookingsForSeatAndUser(int seatNo, int userid);

}
