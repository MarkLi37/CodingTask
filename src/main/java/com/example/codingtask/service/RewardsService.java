package com.example.codingtask.service;

import com.example.codingtask.domain.Rewards;
import com.example.codingtask.entity.Customer;
import com.example.codingtask.entity.Transaction;
import com.example.codingtask.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RewardsService {
    private RewardsServiceUtils rewardsServiceUtils;
    private CustomerRepository customerRepository;
    @Autowired
    public RewardsService(RewardsServiceUtils rewardsServiceUtils, CustomerRepository customerRepository){
        this.rewardsServiceUtils = rewardsServiceUtils;
        this.customerRepository = customerRepository;
    }

    public Customer getCustomerById(Integer customerId){
        return customerRepository.findByCustomerId(customerId);

    }
    @Transactional
    public Rewards getRewardsByCustomerId(Integer customerId){
        return rewardsServiceUtils.getRewardsByCustomerId(customerId);
    }

}
