package com.example.codingtask.controller;

import com.example.codingtask.domain.Rewards;
import com.example.codingtask.entity.Customer;
import com.example.codingtask.entity.Transaction;
import com.example.codingtask.service.RewardsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RewardsController {
    private RewardsService rewardsService;
    private static final Logger log4j = LogManager.getLogger();

    @Autowired
    public RewardsController(RewardsService rewardsService){
        this.rewardsService = rewardsService;
    }
    @GetMapping("/Customer")
    public ResponseEntity<Customer> getCustomerById(@RequestParam Integer customerId){
        Customer customer = rewardsService.getCustomerById(customerId);
        if(customer==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(customer);
    }
    @GetMapping("/Rewards")
    public ResponseEntity getRewardsById(@RequestParam Integer customerId){
        Rewards rewards = rewardsService.getRewardsByCustomerId(customerId);
        Customer customer = rewardsService.getCustomerById(customerId);
        log4j.debug("Customer: "+customer);
        if(customer==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        }
        log4j.debug("Rewards: "+rewards);
        return ResponseEntity.ok().body(rewards);
    }
}
