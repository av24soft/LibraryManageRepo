package com.libraryManagement.dto;

import java.time.LocalDate;

public class SeatDto {
	
	private int rowId;
	private boolean isAvailable;
	private float fees;

	public int getRowId() {
		return rowId;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
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
