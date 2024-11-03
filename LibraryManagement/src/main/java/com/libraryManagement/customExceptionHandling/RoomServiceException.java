package com.libraryManagement.customExceptionHandling;

public class RoomServiceException extends RuntimeException{

	private int code;
	private String message;
	public int getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}

	public RoomServiceException(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public RoomServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	
	
}
