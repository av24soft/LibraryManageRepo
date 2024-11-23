package com.libraryManagement.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryManagement.customExceptionHandling.RowServiceException;
import com.libraryManagement.dto.RowDto;
import com.libraryManagement.entity.Room;
import com.libraryManagement.entity.Row;
import com.libraryManagement.repository.RoomRepository;
import com.libraryManagement.repository.RowRepository;
import com.libraryManagement.service.RowService;

@Service
public class RowServiceImpl implements RowService {

    private static final Logger logger = LoggerFactory.getLogger(RowServiceImpl.class); // Logger initialization

    @Autowired
    RowRepository rowRepository;

    @Autowired
    RoomRepository roomRepository;

    public Row createRow(RowDto rowDto) {

        logger.info("Attempting to create row with name: {}", rowDto.getRowName());

        if (rowDto.getRowName() == null || rowDto.getRowName().isEmpty()) {
            logger.error("Row name is required.");
            throw new RowServiceException("Row name is required");
        }

        if (rowDto.getRowCapacity() <= 0) {
            logger.error("Row capacity must be greater than 0.");
            throw new RowServiceException("Row capacity must be greater than 0");
        }

        Row row = new Row();
        row.setRowName(rowDto.getRowName());
        row.setCapacity(rowDto.getRowCapacity());

        Room room = roomRepository.findById(rowDto.getRoomId()).orElseThrow(() -> {
            logger.error("Invalid room ID: {}", rowDto.getRoomId());
            return new RowServiceException("Invalid Room");
        });

        row.setRoom(room);
        Row savedRow = rowRepository.save(row);

        logger.info("Row created successfully with name: {}", savedRow.getRowName());
        return savedRow;
    }

    @Override
    public void deleteRow(int rowId) {
        logger.info("Attempting to delete row with ID: {}", rowId);

        if (!rowRepository.existsById(rowId)) {
            logger.error("Row with ID {} does not exist.", rowId);
            throw new RowServiceException("Row with ID " + rowId + " does not exist.");
        }

        try {
            rowRepository.deleteById(rowId);
            logger.info("Row with ID {} deleted successfully.", rowId);
        } catch (Exception e) {
            logger.error("Error deleting row with ID {}: {}", rowId, e.getMessage());
            throw new RowServiceException("Failed to delete row with ID " + rowId + ": " + e.getMessage());
        }
    }
}
