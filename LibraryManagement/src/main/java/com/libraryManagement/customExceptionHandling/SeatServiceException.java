package com.libraryManagement.customExceptionHandling;

public class SeatServiceException extends RuntimeException {
    public SeatServiceException(String message) {
        super(message);
    }
}
