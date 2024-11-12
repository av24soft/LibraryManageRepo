package com.libraryManagement.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryManagement.customExceptionHandling.RoomServiceException;
import com.libraryManagement.customExceptionHandling.UserServiceException;
import com.libraryManagement.dto.UserDto;
import com.libraryManagement.entity.Room;
import com.libraryManagement.entity.UserDetails;
import com.libraryManagement.repository.UserRepository;
import com.libraryManagement.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails saveUser(UserDto userDto) {
		try {
		UserDetails userDetails = new UserDetails();
		
		List<UserDetails> users = userRepository.findAll();
		for (UserDetails user : users) {
			if (user.getUserName().equals(userDto.getUserName()) || user.getUserPassword().equals(userDto.getUserPassword())) {
				throw new UserServiceException(409, "User Already Exist");
			}
		}
		
		if (userDto.getDeposit() <= 0) {
			throw new UserServiceException(409, "Please pay deposit amount");
		}
		
		userDetails.setUserName(userDto.getUserName());
		userDetails.setUserAddress(userDto.getUserAddress());
		userDetails.setUserEmail(userDto.getUserEmail());
		userDetails.setUserRole(userDto.getUserRole());
		userDetails.setUserStatus(userDto.getUserStatus());
		userDetails.setUserPassword(userDto.getUserPassword());
		userDetails.setDeposit(userDto.getDeposit());
		
		return userRepository.save(userDetails);
		}
		catch(Exception e) {
			throw new UserServiceException("User Not Created");
		}
	}

}