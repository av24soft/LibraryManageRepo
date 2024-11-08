package com.libraryManagement.dto;

import java.time.LocalDate;

public class CalculationDto {

	private int userid;
	private int seatNo;
	private LocalDate startDate;
	private LocalDate endDate;
	private String bookedStatus;
	private boolean isBooked;
	private float totalFees;
	private String paymentStatus;
	private boolean isCanceled;

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

	public boolean isBooked() {
		return isBooked;
	}

	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}

	public float getTotalFees() {
		return totalFees;
	}

	public void setTotalFees(float totalFees) {
		this.totalFees = totalFees;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public boolean isCanceled() {
		return isCanceled;
	}

	public void setCanceled(boolean isCanceled) {
		this.isCanceled = isCanceled;
	}

	
	public CalculationDto(int userid, int seatNo, LocalDate startDate, LocalDate endDate, String bookedStatus,
			boolean isBooked, float totalFees, String paymentStatus, boolean isCanceled) {
		super();
		this.userid = userid;
		this.seatNo = seatNo;
		this.startDate = startDate;
		this.endDate = endDate;
		this.bookedStatus = bookedStatus;
		this.isBooked = isBooked;
		this.totalFees = totalFees;
		this.paymentStatus = paymentStatus;
		this.isCanceled = isCanceled;
	}

	public CalculationDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
