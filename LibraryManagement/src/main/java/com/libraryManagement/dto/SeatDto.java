package com.libraryManagement.dto;

import java.time.LocalDate;

public class SeatDto {
	private int rowId;
	//private int userDetails;
	
	private LocalDate startDate;
	private LocalDate endDate;
	private boolean  isAvailable;
	private float fees;
	
	public int getRowId() {
		return rowId;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
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

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public float getFees() {
		return fees;
	}

	public void setFees(float fees) {
		this.fees = fees;
	}

	public SeatDto() {
		// TODO Auto-generated constructor stub
	}
	



}
