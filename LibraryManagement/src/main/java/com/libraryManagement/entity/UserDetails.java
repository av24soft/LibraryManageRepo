package com.libraryManagement.entity;



import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import lombok.Data;
@Entity
@Data
public class UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userid;
	@NotEmpty
	@Length(min = 3,max=50)
	private String userName;

	private String userAddress;
	@Email
	private String userEmail;
	@OneToOne(mappedBy = "userDetails",cascade = CascadeType.ALL)
	@JsonManagedReference
	private Seat seat;
	@NotNull
	private String userStatus;
	@NotNull
	private String userRole;
	@NotNull
	private String userPassword;
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
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
	
	public UserDetails(int userid, String userName, String userAddress, String userEmail, Seat seat, String userStatus,
			String userRole, String userPassword) {
		super();
		this.userid = userid;
		this.userName = userName;
		this.userAddress = userAddress;
		this.userEmail = userEmail;
		this.seat = seat;
		this.userStatus = userStatus;
		this.userRole = userRole;
		this.userPassword = userPassword;
	}
	public UserDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}

