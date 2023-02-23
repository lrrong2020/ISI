package com.example.restservice.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restservice.models.Customer;

public interface Dao2 extends JpaRepository<Customer, Integer>{
    

    
}