package com.libraryManagement.service;

import com.libraryManagement.dto.UserDto;
import com.libraryManagement.entity.UserDetails;

public interface UserService {

	public UserDetails saveUser(UserDto userDto);
}
