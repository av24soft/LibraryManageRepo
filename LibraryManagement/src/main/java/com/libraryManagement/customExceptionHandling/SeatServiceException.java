package com.libraryManagement.customExceptionHandling;

public class SeatServiceException extends RuntimeException {
	private String message;
	private int code;
		
		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public SeatServiceException(String message) {
			super();
			this.message = message;
		}

}
