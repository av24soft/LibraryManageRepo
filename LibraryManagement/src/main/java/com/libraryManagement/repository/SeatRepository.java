package com.libraryManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libraryManagement.entity.Seat;
@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {

}

 


