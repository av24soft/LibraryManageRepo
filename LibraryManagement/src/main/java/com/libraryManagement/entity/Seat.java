package com.libraryManagement.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Seat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int seatNo;

	@ManyToOne
	@JoinColumn(name = "rows_rowId", referencedColumnName = "rowId")
	@JsonBackReference
	private Row row;

	@OneToOne
	@JoinColumn(name = "userdetails_userid", referencedColumnName = "userid")
	@JsonBackReference
	private UserDetails userDetails;

	 @OneToOne(mappedBy = "seat", cascade = CascadeType.ALL)
	 @JsonIgnore
	 private Booking booking;
	
	private float fees;
	private boolean isAvailable;
	private String bookedStatus;

	public int getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}

	public Row getRow() {
		return row;
	}

	public void setRow(Row row) {
		this.row = row;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking b) {
		this.booking = b;
	}


	public float getFees() {
		return fees;
	}

	public void setFees(float fees) {
		this.fees = fees;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getBookedStatus() {
		return bookedStatus;
	}

	public void setBookedStatus(String bookedStatus) {
		this.bookedStatus = bookedStatus;
	}


	public Seat(int seatNo, Row row, UserDetails userDetails, Booking booking, 
			    float fees, boolean isAvailable, String bookedStatus) {
		super();
		this.seatNo = seatNo;
		this.row = row;
		this.userDetails = userDetails;
		this.booking = booking;
		this.fees = fees;
		this.isAvailable = isAvailable;
		this.bookedStatus = bookedStatus;
	}

	public Seat() {
		super();
		// TODO Auto-generated constructor stub
	}

}
