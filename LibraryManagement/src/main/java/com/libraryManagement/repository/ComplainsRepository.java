package com.libraryManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libraryManagement.entity.Complains;
@Repository
public interface ComplainsRepository extends JpaRepository<Complains, Integer>{

}
