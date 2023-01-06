package com.example.codingtask.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER")
@Getter
@Setter
public class Customer {
    @Id
    @Column(name = "CUSTOMER_ID")
    private Integer customerId;
    @Column(name = "CUSTOMER_FIRSTNAME")
    private String firstName;
    @Column(name = "CUSTOMER_LASTNAME")
    private String lastName;
}
