package com.libraryManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libraryManagement.entity.Row;

public interface RowRepository extends JpaRepository<Row, Integer>{

}
