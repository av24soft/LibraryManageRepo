package com.libraryManagement.customExceptionHandling;

public class AlreadyBookedException extends RuntimeException {

	private int code;
	private String message;
	public int getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}

	public AlreadyBookedException(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public AlreadyBookedException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}