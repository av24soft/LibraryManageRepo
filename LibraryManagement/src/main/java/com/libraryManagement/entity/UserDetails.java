package com.libraryManagement.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@JsonIgnoreProperties({"userPassword", "userRole","userEmail","userStatus","seat"})
public class UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userid;
	@NotEmpty
	@Length(min = 3, max = 50)
	private String userName;
	private String userAddress;
	@Email
	private String userEmail;
	@OneToMany(mappedBy = "userDetails")
	@JsonManagedReference
	private List<Seat> seats;
	@NotNull
	private String userStatus;
	@NotNull
	private String userRole;
	@NotNull
	private String userPassword;

	@Column(name = "Deposit Amount "+"(\u20B9)")
	private int deposit;

	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Booking> bookings;

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

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
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

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public int getDeposit() {
		return deposit;
	}

	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}

	public UserDetails(int userid, @NotEmpty @Length(min = 3, max = 50) String userName, String userAddress,
			@Email String userEmail, List<Seat> seats, @NotNull String userStatus, @NotNull String userRole,
			@NotNull String userPassword, int deposit, List<Booking> bookings) {
		super();
		this.userid = userid;
		this.userName = userName;
		this.userAddress = userAddress;
		this.userEmail = userEmail;
		this.seats = seats;
		this.userStatus = userStatus;
		this.userRole = userRole;
		this.userPassword = userPassword;
		this.deposit = deposit;
		this.bookings = bookings;
	}

	public UserDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

}
