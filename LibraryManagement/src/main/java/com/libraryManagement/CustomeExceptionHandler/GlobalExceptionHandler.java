package com.libraryManagement.CustomeExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RoomServiceException.class)
	public ResponseEntity roomServiceException(RoomServiceException roomServiceException)
	{
		return new ResponseEntity(roomServiceException.getMessage(),HttpStatusCode.valueOf(roomServiceException.getCode()));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity exception(Exception exception)
	{
		return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
	}
}
