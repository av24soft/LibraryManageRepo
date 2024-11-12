package com.libraryManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libraryManagement.dto.RowDto;
import com.libraryManagement.entity.Row;
import com.libraryManagement.service.RowService;

@RestController
@RequestMapping("/row")
public class RowController {

	@Autowired
	RowService rowService;

	@PostMapping("/create")
	public ResponseEntity<Row> createRow(@RequestBody RowDto rowDto) {

		return new ResponseEntity<Row>(rowService.createRow(rowDto), HttpStatus.CREATED);
	}

}
