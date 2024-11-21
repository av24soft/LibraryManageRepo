package com.libraryManagement.customExceptionHandling;

public class UploadDownloadException extends RuntimeException {

	private int code;
	private String message;

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public UploadDownloadException(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public UploadDownloadException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}