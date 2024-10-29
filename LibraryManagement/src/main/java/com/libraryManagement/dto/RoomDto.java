package com.libraryManagement.dto;

public class RoomDto {

	private String roomName;

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public RoomDto(String roomName) {
		super();
		this.roomName = roomName;
	}

	public RoomDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
