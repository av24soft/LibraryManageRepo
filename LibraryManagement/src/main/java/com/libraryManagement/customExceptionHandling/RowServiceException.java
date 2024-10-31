package com.libraryManagement.customExceptionHandling;

public class RowServiceException extends RuntimeException{
	
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public RowServiceException(String message) {
		super();
		this.message = message;
	}


	

}
