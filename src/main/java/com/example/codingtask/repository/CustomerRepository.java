package com.example.codingtask.repository;

import com.example.codingtask.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findByCustomerId(Integer customerId);
}
