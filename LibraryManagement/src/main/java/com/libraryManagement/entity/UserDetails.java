package com.libraryManagement.entity;



import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userid;
	private String userName;
	private String userAddress;
	@OneToOne(mappedBy = "userDetails",cascade = CascadeType.ALL)
	@JsonManagedReference
	private Seat seat;
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public Seat getSeat() {
		return seat;
	}
	public void setSeat(Seat seat) {
		this.seat = seat;
	}
	public UserDetails(int userid, String userName, String userAddress, Seat seat) {
		super();
		this.userid = userid;
		this.userName = userName;
		this.userAddress = userAddress;
		this.seat = seat;
	}
	public UserDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}

