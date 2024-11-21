package com.libraryManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libraryManagement.dto.TransactionDto;
import com.libraryManagement.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	TransactionService transactionService;
	
	@PostMapping
	public ResponseEntity createTransaction(@RequestBody TransactionDto transactionDto)
	{
	    transactionService.createTransaction(transactionDto);	
		return new ResponseEntity(HttpStatus.CREATED);
	}
}
