package com.libraryManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libraryManagement.entity.UserDetails;

public interface UserRepository extends JpaRepository<UserDetails, Integer> {

}
