package com.libraryManagement.entity;


import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
	@JoinColumn(name="rows_rowId",referencedColumnName = "rowId")
	@JsonBackReference
	private Row row;
	
	@OneToOne
	@JoinColumn(name="userdetails_userid",referencedColumnName = "userid")
	@JsonBackReference
	private UserDetails userDetails;
	

	private LocalDate startDate;
	private LocalDate endDate;
	private float fees;
	private boolean isAvailable;
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
	public Seat(int seatNo, Row row, UserDetails userDetails, LocalDate startDate, LocalDate endDate, float fees,
			boolean isAvailable) {
		super();
		this.seatNo = seatNo;
		this.row = row;
		this.userDetails = userDetails;
		this.startDate = startDate;
		this.endDate = endDate;
		this.fees = fees;
		this.isAvailable = isAvailable;
	}
	public Seat() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
		
}

