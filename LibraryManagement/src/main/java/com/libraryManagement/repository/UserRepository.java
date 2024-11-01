package com.libraryManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libraryManagement.entity.UserDetails;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Integer>{

}
