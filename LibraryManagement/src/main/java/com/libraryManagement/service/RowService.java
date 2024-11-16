package com.libraryManagement.service;

import com.libraryManagement.dto.RowDto;
import com.libraryManagement.entity.Row;

public interface RowService {
	
	public Row createRow(RowDto rowDto);
	public void deleteRow(int rowId);
	
}
