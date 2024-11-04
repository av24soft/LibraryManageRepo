package com.libraryManagement.customExceptionHandling;

public class UserDoesNotExists  extends RuntimeException {
	
	private int code;
	private String message;
	public int getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}

	public UserDoesNotExists(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public UserDoesNotExists(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
