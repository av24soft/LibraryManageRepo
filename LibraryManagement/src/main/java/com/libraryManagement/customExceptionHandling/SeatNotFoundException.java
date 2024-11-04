
package com.libraryManagement.customExceptionHandling;

public class SeatNotFoundException extends RuntimeException {

	private int code;
	private String message;
	public int getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}

	public SeatNotFoundException(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public SeatNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}
