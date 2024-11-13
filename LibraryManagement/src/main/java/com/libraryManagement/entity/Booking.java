package com.libraryManagement.entity;

import java.time.LocalDate;

import org.apache.catalina.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingId;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Seat_No.", referencedColumnName = "seatNo")
	private Seat seat;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "User_Id", referencedColumnName = "userid")
	private UserDetails user;
	
	private LocalDate startDate;
	private LocalDate endDate;
	@Column(name = "totalFees "+"(\u20B9)")
	private int totalFees;
	private String bookingStatus;
	
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public Seat getSeat() {
		return seat;
	}
	public void setSeat(Seat seat) {
		this.seat = seat;
	}
	
	public UserDetails getUser() {
		return user;
	}
	public void setUser(UserDetails user) {
		this.user = user;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public int getTotalFees() {
		return totalFees;
	}
	public void setTotalFees(int totalFees) {
		this.totalFees = totalFees;
	}
	
	public String getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	public Booking() {
		super();
	}
	
	


}
