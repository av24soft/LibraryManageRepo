package com.libraryManagement.globalExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.libraryManagement.customExceptionHandling.AlreadyBookedException;
import com.libraryManagement.customExceptionHandling.RoomServiceException;
import com.libraryManagement.customExceptionHandling.RowServiceException;
import com.libraryManagement.customExceptionHandling.SeatNotFoundException;
import com.libraryManagement.customExceptionHandling.UserDoesNotExists;

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
	
	
	
	@ExceptionHandler(AlreadyBookedException.class)
    public ResponseEntity<String> handleSeatAlreadyBookedException(AlreadyBookedException alreadyBookedException) {
        return new ResponseEntity<>(alreadyBookedException.getMessage(), HttpStatus.valueOf(alreadyBookedException.getCode()));
    
    }

    @ExceptionHandler(SeatNotFoundException.class)
    public ResponseEntity<String> handleSeatNotFoundException(SeatNotFoundException seatNotFoundException) {
        return new ResponseEntity<>(seatNotFoundException.getMessage(), HttpStatus.valueOf(seatNotFoundException.getCode()));
    }

    @ExceptionHandler(UserDoesNotExists.class)
    public ResponseEntity<String> handleUserNotFoundException(UserDoesNotExists userDoesNotExists) {
        return new ResponseEntity<>(userDoesNotExists.getMessage(), HttpStatus.valueOf(userDoesNotExists.getCode()));
    }

	@ExceptionHandler(Exception.class)
	public ResponseEntity exception(Exception exception)
	{
		return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
	}
}
