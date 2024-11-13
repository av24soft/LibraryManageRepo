package com.libraryManagement.customExceptionHandling;

@SuppressWarnings("serial")
public class UserServiceException extends RuntimeException{

	private int code;
	private String message;
	public int getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}

	public UserServiceException(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public UserServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	
}
