package com.libraryManagement.repository;

import com.libraryManagement.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SeatRepository extends JpaRepository<Seat,Integer> {
    List<Seat> findByEndDate(LocalDate notificationDate);
}
