package com.libraryManagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libraryManagement.entity.UserDetails;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Integer>{

	Optional<UserDetails> findByUserPassword(String userPassword);

	Optional<UserDetails> findByUserName(String userName);

}
