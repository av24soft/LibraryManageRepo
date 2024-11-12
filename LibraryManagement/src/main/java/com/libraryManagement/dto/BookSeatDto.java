
package com.libraryManagement.dto;


public class BookSeatDto {

	private int bookingId;
	
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	
	public BookSeatDto() {
		super();
	}
	public BookSeatDto(int bookingId) {
		super();
		this.bookingId = bookingId;
		
	}
	
	
	
	
}

