package com.libraryManagement.service;

import java.util.List;

import com.libraryManagement.dto.RoomDto;
import com.libraryManagement.entity.Room;

public interface RoomService {

	public void createRoom(RoomDto roomDto);

	public List<Room> getAllRoom();

	public Room getRoom(int id);
	
	public void  deleteRoom(int id);

	

	
}

