package com.libraryManagement.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryManagement.customExceptionHandling.UserServiceException;
import com.libraryManagement.dto.UserDto;
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

			Optional<UserDetails> existingUser = userRepository.findByUserName(userDto.getUserName());
			
			if (existingUser.isPresent()) {
				
				throw new UserServiceException(409, "User Already Exist with the same username");
			}

			Optional<UserDetails> existingPassword = userRepository.findByUserPassword(userDto.getUserPassword());
			
			if (existingPassword.isPresent()) {
				
				throw new UserServiceException(409, "User Already Exist with the same password");
			}

			userDetails.setUserName(userDto.getUserName());
			userDetails.setUserAddress(userDto.getUserAddress());
			userDetails.setUserEmail(userDto.getUserEmail());
			userDetails.setUserRole(userDto.getUserRole());
			userDetails.setUserStatus(userDto.getUserStatus());
			userDetails.setUserPassword(userDto.getUserPassword());
			userDetails.setDeposit(0);
			return userRepository.save(userDetails);
		}
		catch (Exception e) {
			
            throw new UserServiceException(400, e.getMessage());
		}
	}

	@Override
	public UserDto getUser(Integer userId) {

		try {

			Optional<UserDetails> getuser = userRepository.findById(userId);

			if (getuser.isEmpty()) {
				throw new UserServiceException(404, "User Not Found Exception ");
			}

			UserDetails user = getuser.get();

			UserDto userDto = new UserDto();
			userDto.setUserid(user.getUserid());
			userDto.setUserName(user.getUserName());
			userDto.setUserAddress(user.getUserAddress());
			userDto.setUserEmail(user.getUserEmail());
			userDto.setUserStatus(user.getUserStatus());
			userDto.setUserRole(user.getUserRole());
			return userDto;
		} catch (Exception e) {
			throw new UserServiceException(400, e.getMessage());
		}
	}


}