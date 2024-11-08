
package com.libraryManagement.dto;

import java.time.LocalDate;

public class BookSeatDto {

	private int bookingId;
	private boolean isBooked;
	private String paymentStatus;
	private boolean isAvailable;
	private String bookedStatus;
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public boolean isBooked() {
		return isBooked;
	}
	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public String getBookedStatus() {
		return bookedStatus;
	}
	public void setBookedStatus(String bookedStatus) {
		this.bookedStatus = bookedStatus;
	}
	public BookSeatDto() {
		super();
	}
	public BookSeatDto(int bookingId, boolean isBooked, String paymentStatus, boolean isAvailable,
			String bookedStatus) {
		super();
		this.bookingId = bookingId;
		this.isBooked = isBooked;
		this.paymentStatus = paymentStatus;
		this.isAvailable = isAvailable;
		this.bookedStatus = bookedStatus;
	}
	
	
	
	
}

