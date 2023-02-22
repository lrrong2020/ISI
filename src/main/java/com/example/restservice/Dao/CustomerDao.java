package com.example.restservice.Dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.restservice.models.Customer;


@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer>{
	public HashMap<Integer, Customer> getAllCustomers();
    public Customer getCustomerById(String customerId);
    public void saveCustomer(Customer customer);
    public void deleteCustomer(int customerId);
}