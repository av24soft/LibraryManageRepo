package com.libraryManagement.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryManagement.dto.RoomDto;
import com.libraryManagement.entity.Room;
import com.libraryManagement.repository.RoomRepository;
import com.libraryManagement.service.RoomService;
@Service
public class RoomServiceImpl implements RoomService{

	@Autowired
	RoomRepository roomRepository;
	@Override
	public void createRoom(RoomDto roomDto) {
		// TODO Auto-generated method stub
		Room room = new Room();
		room.setRoomName(roomDto.getRoomName());
		roomRepository.save(room);
	}

}
