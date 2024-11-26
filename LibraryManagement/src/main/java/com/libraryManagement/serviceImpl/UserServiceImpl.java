package com.libraryManagement.serviceImpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public UserDetails saveUser(UserDto userDto) {
		try {
            logger.info("Attempting to save user with username: {}", userDto.getUserName());

			UserDetails userDetails = new UserDetails();

			Optional<UserDetails> existingUser = userRepository.findByUserName(userDto.getUserName());

			if (existingUser.isPresent()) {
                logger.error("User already exists with the username: {}", userDto.getUserName());
				throw new UserServiceException(409, "User Already Exist with the same username");
			}

			Optional<UserDetails> existingPassword = userRepository.findByUserPassword(userDto.getUserPassword());

			if (existingPassword.isPresent()) {
                logger.error("User already exists with the same password for user: {}", userDto.getUserName());
				throw new UserServiceException(409, "User Already Exist with the same password");
			}

			userDetails.setUserName(userDto.getUserName());
			userDetails.setUserAddress(userDto.getUserAddress());
			userDetails.setUserEmail(userDto.getUserEmail());
			userDetails.setUserRole(userDto.getUserRole());
			userDetails.setUserStatus(userDto.getUserStatus());
			userDetails.setUserPassword(userDto.getUserPassword());
			userDetails.setDeposit(0);
            logger.info("User successfully saved with username: {}", userDto.getUserName());
			return userRepository.save(userDetails);
		} catch (Exception e) {
            logger.error("User creation failed: {}", e.getMessage());
			throw new UserServiceException(400, e.getMessage());
		}
	}

	@Override
	public UserDetails getUser(Integer userId) {

		try {
            logger.info("Fetching user details for user ID: {}", userId);

			Optional<UserDetails> getuser = userRepository.findById(userId);

			if (getuser.isEmpty()) {
                logger.error("User not found with ID: {}", userId);

				throw new UserServiceException(404, "User Not Found Exception ");
			}

			UserDetails user = getuser.get();
            logger.info("Successfully fetched user details for user ID: {}", userId);

			return user;
		} catch (Exception e) {
            logger.info("Successfully fetched user details for user ID: {}", userId);

			throw new UserServiceException(400, e.getMessage());
		}
	}

}