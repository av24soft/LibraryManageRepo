package com.libraryManagement.dto;

public class TransactionDto {

	private int userId;
	private int amount;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public TransactionDto(int userId, int amount) {
		super();
		this.userId = userId;
		this.amount = amount;
	}
}
