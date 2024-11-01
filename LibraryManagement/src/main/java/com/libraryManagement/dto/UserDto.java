package com.libraryManagement.dto;

public class UserDto {

	private String userName;
	private String userAddress;
	private String email;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public UserDto(String userName, String userAddress, String email) {
		super();
		this.userName = userName;
		this.userAddress = userAddress;
		this.email = email;
	}
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
