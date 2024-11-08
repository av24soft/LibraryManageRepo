
package com.libraryManagement.customExceptionHandling;

public class BookSeatException extends RuntimeException{

	
	private int code;
	private String message;
	public int getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}

	public BookSeatException(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public BookSeatException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}


	
	
}
