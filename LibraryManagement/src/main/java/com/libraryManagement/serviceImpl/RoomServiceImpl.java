package com.libraryManagement.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryManagement.customExceptionHandling.RoomServiceException;
import com.libraryManagement.customExceptionHandling.RowServiceException;
import com.libraryManagement.dto.RoomDto;
import com.libraryManagement.entity.Room;
import com.libraryManagement.entity.Row;
import com.libraryManagement.entity.Seat;
import com.libraryManagement.repository.RoomRepository;
import com.libraryManagement.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	RoomRepository roomRepository;

	@Override
	public Room createRoom(RoomDto roomDto) {
		List<Room> rooms = roomRepository.findAll();
		for (Room room : rooms) {
			if (room.getRoomName().equals(roomDto.getRoomName())) {
				throw new RoomServiceException(409, "Room Name Already Exist");
			}
		}
		Room room = new Room();
		room.setRoomName(roomDto.getRoomName());
		return roomRepository.save(room);
	}
	

	public List<Room> getAllRoom() {
		try {
			List<Room> rooms = roomRepository.findAll();
			if (rooms == null || rooms.isEmpty()) {
				throw new RoomServiceException(409, "Rooms Not Found");
			}
			return rooms;
		} catch (Exception e) {
			throw new RoomServiceException(500, "Error fetching rooms: " + e.getMessage());
		}
	}

	@Override
	public Room getRoom(Integer id) {
		try {
			return roomRepository.findById(id).orElseThrow(() -> new RoomServiceException(409, "Room Not Found"));
		} catch (RoomServiceException e) {
			throw new RoomServiceException(409, "Room Not Found");
		} catch (Exception e) {
			throw new RoomServiceException(500, "Error fetching room: " + e.getMessage());
		}
	}
	@Override
	public void deleteRoom(int id) {
		Room room = roomRepository.findById(id)
				.orElseThrow(() -> new RowServiceException("Room is not found."));
		 
		for(Row row : room.getRows()) {
		for (Seat seat : row.getSeat()) {
		        if (!seat.isAvailable()) {
		            throw new RowServiceException("room cannot deleted, in that room seat is booked.");
		        }}
		roomRepository.deleteById(id);
		
}
}
}