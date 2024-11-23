package com.libraryManagement.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryManagement.customExceptionHandling.RowServiceException;
import com.libraryManagement.dto.RowDto;
import com.libraryManagement.entity.Room;
import com.libraryManagement.entity.Row;
import com.libraryManagement.entity.Seat;
import com.libraryManagement.repository.RoomRepository;
import com.libraryManagement.repository.RowRepository;
import com.libraryManagement.service.RowService;

@Service
public class RowServiceImpl implements RowService {

	@Autowired
	RowRepository rowRepository;

	@Autowired
	RoomRepository roomRepository;

	public Row createRow(RowDto rowDto) {

		if (rowDto.getRowName() == null || rowDto.getRowName().isEmpty()) {

			throw new RowServiceException("Row name is required");
		}

		if (rowDto.getRowCapacity() <= 0) {

			throw new RowServiceException("Row capacity must be greater than 0");
		}

		Row row = new Row();

		row.setRowName(rowDto.getRowName());
		row.setCapacity(rowDto.getRowCapacity());

		Room room = roomRepository.findById(rowDto.getRoomId()).get();

		if (room == null) {

			throw new RowServiceException("Invalid Room");
		}

		row.setRoom(room);

		return rowRepository.save(row);
	}
	@Override
	public void deleteRow(int rowId) {
	    Row row = rowRepository.findById(rowId)
	            .orElseThrow(() -> new RowServiceException("Row with ID " + rowId + " is not found."));

	    for (Seat seat : row.getSeat()) {
	        if (!seat.isAvailable()) {
	            throw new RowServiceException("row cannot deleted, in that row seat is booked.");
	        }
	    }

	    rowRepository.deleteById(rowId);
	    System.out.println("Row with ID " + rowId + " deleted successfully.");
	}

}
