package com.libraryManagement.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true)
	private int transactionNo;
	private String status;
	@ManyToOne
	@JoinColumn(name = "userdetails_userid", referencedColumnName = "userid")
	@JsonBackReference
	private UserDetails userDetails;
	private int amount;
	private LocalDate transactionDate;

	@OneToOne(mappedBy = "transaction")
	Booking booking;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTransactionNo() {
		return transactionNo;
	}

	public void setTransactionNo(int transactionNo) {
		this.transactionNo = transactionNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Transaction(int id, int transactionNo, String status, UserDetails userDetails, int amount,
			LocalDate transactionDate, Booking booking) {
		super();
		this.id = id;
		this.transactionNo = transactionNo;
		this.status = status;
		this.userDetails = userDetails;
		this.amount = amount;
		this.transactionDate = transactionDate;
		this.booking = booking;
	}

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

}
