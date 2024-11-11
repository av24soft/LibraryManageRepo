package com.libraryManagement.globalExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.libraryManagement.customExceptionHandling.BookSeatException;
import com.libraryManagement.customExceptionHandling.RoomServiceException;
import com.libraryManagement.customExceptionHandling.RowServiceException;
import com.libraryManagement.customExceptionHandling.SeatServiceException;
import com.libraryManagement.customExceptionHandling.TransactionServiceException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RoomServiceException.class)
	public ResponseEntity roomServiceException(RoomServiceException roomServiceException) {
		return new ResponseEntity(roomServiceException.getMessage(),
				HttpStatusCode.valueOf(roomServiceException.getCode()));
	}

	@ExceptionHandler(RowServiceException.class)
	public ResponseEntity handleRowServiceException(RowServiceException exception) {
		return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(SeatServiceException.class)
	public ResponseEntity handleSeatServiceException(SeatServiceException exception) {
		return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BookSeatException.class)
	public ResponseEntity bookSeatException(BookSeatException bookSeatException) {

		return new ResponseEntity(bookSeatException.getMessage(), HttpStatusCode.valueOf(bookSeatException.getCode()));

	}
	@ExceptionHandler(TransactionServiceException.class)
	public ResponseEntity transactionServiceException(TransactionServiceException transactionServiceException) {

		return new ResponseEntity(transactionServiceException.getMessage(), HttpStatusCode.valueOf(transactionServiceException.getCode()));

	}


	@ExceptionHandler(Exception.class)
	public ResponseEntity exception(Exception exception) {
		return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
