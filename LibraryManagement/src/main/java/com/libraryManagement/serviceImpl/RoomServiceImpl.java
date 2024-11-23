package com.libraryManagement.serviceImpl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryManagement.customExceptionHandling.RoomServiceException;
import com.libraryManagement.customExceptionHandling.RowServiceException;
import com.libraryManagement.dto.RoomDto;
import com.libraryManagement.entity.Room;
import com.libraryManagement.repository.RoomRepository;
import com.libraryManagement.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService {

    private static final Logger logger = LoggerFactory.getLogger(RoomServiceImpl.class); // Logger initialization

    @Autowired
    RoomRepository roomRepository;

    @Override
    public Room createRoom(RoomDto roomDto) {
        logger.info("Attempting to create room with name: {}", roomDto.getRoomName());

        List<Room> rooms = roomRepository.findAll();
        for (Room room : rooms) {
            if (room.getRoomName().equals(roomDto.getRoomName())) {
                logger.error("Room with name {} already exists.", roomDto.getRoomName());
                throw new RoomServiceException(409, "Room Name Already Exist");
            }
        }

        Room room = new Room();
        room.setRoomName(roomDto.getRoomName());
        Room savedRoom = roomRepository.save(room);

        logger.info("Room created successfully with name: {}", savedRoom.getRoomName());
        return savedRoom;
    }

    @Override
    public List<Room> getAllRoom() {
        logger.info("Fetching all rooms.");

        try {
            List<Room> rooms = roomRepository.findAll();
            if (rooms == null || rooms.isEmpty()) {
                logger.error("No rooms found.");
                throw new RoomServiceException(409, "Rooms Not Found");
            }

            logger.info("Successfully fetched {} rooms.", rooms.size());
            return rooms;
        } catch (Exception e) {
            logger.error("Error fetching rooms: {}", e.getMessage());
            throw new RoomServiceException(500, "Error fetching rooms: " + e.getMessage());
        }
    }

    @Override
    public Room getRoom(Integer id) {
        logger.info("Fetching room with ID: {}", id);

        try {
            return roomRepository.findById(id).orElseThrow(() -> {
                logger.error("Room not found with ID: {}", id);
                return new RoomServiceException(409, "Room Not Found");
            });
        } catch (RoomServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Error fetching room with ID {}: {}", id, e.getMessage());
            throw new RoomServiceException(500, "Error fetching room: " + e.getMessage());
        }
    }

    @Override
    public void deleteRoom(int id) {
        logger.info("Attempting to delete room with ID: {}", id);

        if (!roomRepository.existsById(id)) {
            logger.error("Room with ID {} does not exist.", id);
            throw new RowServiceException("Room with ID " + id + " does not exist.");
        }

        try {
            roomRepository.deleteById(id);
            logger.info("Room with ID {} deleted successfully.", id);
        } catch (Exception e) {
            logger.error("Error deleting room with ID {}: {}", id, e.getMessage());
            throw new RoomServiceException("Failed to delete room with ID " + id + ": " + e.getMessage());
        }
    }
}
