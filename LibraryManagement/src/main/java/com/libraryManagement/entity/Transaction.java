package com.libraryManagement.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int transactionNo;
	private String status;
	@ManyToOne
	@JoinColumn(name="userdetails_userid",referencedColumnName = "userid")
	@JsonBackReference
	private UserDetails userDetails;
	private int amount;
	private LocalDate transactionDate;
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
	public Transaction(int id, int transactionNo, String status, UserDetails userDetails, int amount,
			LocalDate transactionDate) {
		super();
		this.id = id;
		this.transactionNo = transactionNo;
		this.status = status;
		this.userDetails = userDetails;
		this.amount = amount;
		this.transactionDate = transactionDate;
	}
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
