package com.libraryManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libraryManagement.dto.UserDto;
import com.libraryManagement.entity.UserDetails;
import com.libraryManagement.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping
	public ResponseEntity<UserDetails> createUser(@RequestBody UserDto userDto) {
		return new ResponseEntity<UserDetails>(userService.saveUser(userDto), HttpStatus.CREATED);

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUser(@PathVariable Integer id) {
	    UserDto userDto = userService.getUser(id);
	    return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}
	

}
