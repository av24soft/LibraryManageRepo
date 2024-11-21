
package com.libraryManagement.customExceptionHandling;

public class TransactionServiceException extends RuntimeException{

	
	private int code;
	private String message;
	public int getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}

	public TransactionServiceException(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public TransactionServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}


	
	
}
