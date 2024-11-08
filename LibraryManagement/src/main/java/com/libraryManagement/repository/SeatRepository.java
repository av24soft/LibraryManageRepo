package com.libraryManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.libraryManagement.entity.Seat;
@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {

	 @Query("SELECT s FROM Seat s")
	    List<Seat> findAllSeats();
}

 


