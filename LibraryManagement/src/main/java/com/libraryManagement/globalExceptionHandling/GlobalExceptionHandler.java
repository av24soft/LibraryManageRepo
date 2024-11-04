package com.libraryManagement.globalExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.libraryManagement.customExceptionHandling.RoomServiceException;
import com.libraryManagement.customExceptionHandling.RowServiceException;
import com.libraryManagement.customExceptionHandling.SeatServiceException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RoomServiceException.class)
	public ResponseEntity roomServiceException(RoomServiceException roomServiceException)
	{
		return new ResponseEntity(roomServiceException.getMessage(),HttpStatusCode.valueOf(roomServiceException.getCode()));
	}
	
	
	@ExceptionHandler(RowServiceException.class)
	public ResponseEntity handleRowServiceException(RowServiceException exception)
	{
		return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(SeatServiceException.class)
	public ResponseEntity handleSeatServiceException(SeatServiceException exception)
	{
		return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
	}

	
	@ExceptionHandler(Exception.class)
	public ResponseEntity exception(Exception exception)
	{
		return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
	}
}
