package com.libraryManagement.controller;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libraryManagement.dto.RoomDto;
import com.libraryManagement.entity.Room;
import com.libraryManagement.service.RoomService;

@RestController
	
@RequestMapping("/room")
public class RoomController {

	@Autowired
	RoomService roomServiceImpl;

	@PostMapping
	public ResponseEntity<Room> createRoom(@RequestBody RoomDto roomDto) {
		return new ResponseEntity<Room>(roomServiceImpl.createRoom(roomDto), HttpStatus.CREATED);
	}

	@CrossOrigin(origins = "*")
	@GetMapping
	public ResponseEntity getAllRooms() {
		List<Room> room = roomServiceImpl.getAllRoom();
		return new ResponseEntity(room, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity getRoomById(@PathVariable("id") Integer id) {
		return new ResponseEntity(roomServiceImpl.getRoom(id), HttpStatus.OK);

	}
	 @DeleteMapping("/{id}")
     public ResponseEntity deleteRoom(@PathVariable("id") Integer id) {
         roomServiceImpl.deleteRoom(id);
         return new ResponseEntity (HttpStatus.OK);
     }

}
