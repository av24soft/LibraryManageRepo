
package com.libraryManagement.dto;

import java.time.LocalDate;

public class BookSeatDto {

	private int userid;
	private int seatNo;
	private LocalDate startDate;
	private LocalDate endDate;
	private String bookedStatus;

	public String getBookedStatus() {
		return bookedStatus;
	}

	public void setBookedStatus(String bookedStatus) {
		this.bookedStatus = bookedStatus;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public BookSeatDto(int userid, int seatNo, LocalDate startDate, LocalDate endDate, String bookedStatus) {
		super();
		this.userid = userid;
		this.seatNo = seatNo;
		this.startDate = startDate;
		this.endDate = endDate;
		this.bookedStatus = bookedStatus;
	}

	public BookSeatDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}

