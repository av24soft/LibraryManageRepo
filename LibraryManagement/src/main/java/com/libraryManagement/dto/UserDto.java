package com.libraryManagement.dto;

public class UserDto {
	private String userName;
	private String userAddress;
	private String userEmail;
	private String userStatus;
	private String userRole;
	public UserDto(String userName, String userAddress, String userEmail, String userStatus, String userRole,
			String userPassword) {
		super();
		this.userName = userName;
		this.userAddress = userAddress;
		this.userEmail = userEmail;
		this.userStatus = userStatus;
		this.userRole = userRole;
		this.userPassword = userPassword;
	}
	private String userPassword;
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
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
