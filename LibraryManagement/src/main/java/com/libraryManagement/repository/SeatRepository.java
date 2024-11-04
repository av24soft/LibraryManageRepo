package com.libraryManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libraryManagement.entity.Seat;

public interface SeatRepository extends JpaRepository<Seat, Integer> {

}
