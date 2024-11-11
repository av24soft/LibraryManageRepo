package com.libraryManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libraryManagement.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer>{

}
