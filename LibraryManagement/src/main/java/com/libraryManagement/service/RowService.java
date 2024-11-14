package com.libraryManagement.service;

import com.libraryManagement.dto.RowDto;

public interface RowService {
	
	public void createRow(RowDto rowDto);
	public void deleteRow(int rowId);
	
}
