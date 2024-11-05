package com.libraryManagement.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryManagement.repository.RoomRepository;
import com.libraryManagement.repository.RowRepository;
import com.libraryManagement.repository.UserRepository;
import com.libraryManagement.service.VacandSeatService;
@Service
public class VacandSeatServiceImpl implements VacandSeatService{

	@Autowired
	RoomRepository roomRepository;
	@Autowired
	RowRepository rowRepository;
	@Autowired
	UserRepository userRepository;
	@Override
	public void showSeat(int id) {
		roomRepository.getById(id);
		
	}
	@Override
	public void showRoom(int id) {
		roomRepository.getById(id);
		
	}
	@Override
	public void showUser(int id) {
		
		userRepository.getById(id);
	}

}
