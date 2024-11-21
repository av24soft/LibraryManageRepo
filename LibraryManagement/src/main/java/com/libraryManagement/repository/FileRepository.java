package com.libraryManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libraryManagement.entity.Storage;

public interface FileRepository extends JpaRepository<Storage, Integer> {

}
