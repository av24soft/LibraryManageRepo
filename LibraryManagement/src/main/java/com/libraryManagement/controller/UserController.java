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
		UserDetails user = userService.saveUser(userDto);
		return new ResponseEntity<UserDetails>(user, HttpStatus.CREATED);

	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDetails> getUser(@PathVariable Integer id) {
		return new ResponseEntity<UserDetails>(userService.getUser(id), HttpStatus.OK);
	}

}
