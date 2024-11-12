package com.libraryManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libraryManagement.entity.Transaction;
@Repository
public interface TransactionRepositry extends JpaRepository<Transaction, Integer> {

}
