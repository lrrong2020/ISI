package com.example.restservice.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.restservice.Dao.CustomerDao;
import com.example.restservice.models.Customer;

@Component
public class CustomerService{
	
	@Autowired
	private CustomerDao dao;
	
	public String addCustomer(Customer customer) {
		dao.saveCustomer(customer);
		return "The new customer has been added into database, Id is " + customer.getCustomerId();
	}
	public Customer getCustomer(int customerId) {
		return dao.getById(customerId);
	}
	public List<Customer> getAllCustomers() {
		return dao.findAll();
	}
	public String deleteCustomer(int customerId) {
		dao.deleteCustomer(customerId);
		return "Customer Id is deleted: " + customerId;
	}
	public String updateCustomer(Customer customer) {
		dao.saveCustomer(customer);
		return "Customer Id is updated: "+customer.getCustomerId();
	}
	 
	

}