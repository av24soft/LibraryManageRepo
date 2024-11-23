package com.libraryManagement.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.libraryManagement.entity.Booking;

import jakarta.transaction.Transactional;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

	@Query("SELECT b FROM Booking b WHERE b.seat.seatNo = :seatNo AND b.user.userid = :userid")
	List<Booking> findBookingsForSeatAndUser(int seatNo, int userid);

	@Query("SELECT b FROM Booking b WHERE b.bookingStatus = 'Processing...' AND b.bookingTime < :cutoffTime")
	List<Booking> findProcessingBookingsOlderThan5Minutes(@Param("cutoffTime") LocalDateTime cutoffTime);

	@Modifying
	@Transactional
	@Query("DELETE FROM Booking b WHERE b.bookingStatus = 'Processing...' AND b.bookingTime < :cutoffTime")
	void deleteOldProcessingBookings(@Param("cutoffTime") LocalDateTime cutoffTime);

}
