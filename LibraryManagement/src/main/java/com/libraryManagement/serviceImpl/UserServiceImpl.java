package com.libraryManagement.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryManagement.dto.UserDto;
import com.libraryManagement.entity.UserDetails;
import com.libraryManagement.repository.UserRepository;
import com.libraryManagement.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	@Override
	public void saveUser(UserDto userDto) {
		UserDetails userDetails = new UserDetails();
		userDetails.setUserName(userDto.getUserName());
		userDetails.setUserAddress(userDto.getUserAddress());
		userDetails.setUserEmail(userDto.getUserEmail());
		userDetails.setUserRole(userDto.getUserRole());
		userDetails.setUserStatus(userDto.getUserStatus());
		userDetails.setUserPassword(userDto.getUserPassword());
		userRepository.save(userDetails);
		
	}

}