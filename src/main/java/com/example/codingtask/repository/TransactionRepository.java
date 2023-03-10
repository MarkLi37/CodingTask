package com.example.codingtask.repository;

import com.example.codingtask.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    List<Transaction> findAllByCustomerId(Integer customerId);
    List<Transaction> findAllByCustomerIdAndTransactionDateBetween(Integer customerId,Timestamp startDate,Timestamp endDate);
}
