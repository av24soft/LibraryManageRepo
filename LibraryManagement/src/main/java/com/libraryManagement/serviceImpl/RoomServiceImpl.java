package com.libraryManagement.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryManagement.customExceptionHandling.RoomServiceException;
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
		List<Room> rooms=roomRepository.findAll();
		for(Room room:rooms)
		{
			if(room.getRoomName().equals(roomDto.getRoomName()))
			{
				 throw new RoomServiceException(409, "Room Name Already Exist");
			}
		}
		Room room = new Room();
		room.setRoomName(roomDto.getRoomName());
		roomRepository.save(room);
	}
	@Override
	public List<Room> getAllRoom() {
		List<Room> room = roomRepository.findAll();
		if(room ==null) {
			throw new RoomServiceException(409, "Room Not Found");
		}
		return room;
	}
	@Override
	public Room getRoom(Integer id) {
		
		Room room = roomRepository.findById(id).get();
		if( room ==null) {
			throw new RoomServiceException(409, "Room Not Found");
		}
		return room;
	}
}

