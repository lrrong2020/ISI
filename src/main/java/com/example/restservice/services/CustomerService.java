package com.example.restservice.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.restservice.Dao.CustomerDaoImpl;
import com.example.restservice.Dao.Dao;

import org.springframework.stereotype.Component;

import com.example.restservice.models.Customer;

@Component
public class CustomerService{

	@Autowired
	private Dao<Customer> customerDao= new CustomerDaoImpl();
	
	public String addCustomer(Customer customer) {
		customerDao.save(customer);
		return "The new customer has been added into database, Id is " + customer.getCustomerId();
	}
	public Customer getCustomer(Integer customerId) {
		return (Customer) customerDao.get(customerId);
	}
	public List<Customer> getAllCustomers() {
		return customerDao.getAll();
	}
	public String deleteCustomer(Customer customer) {
		customerDao.delete(customer);
		return "Customer Id is deleted: " + customer.getCustomerId();
	}
	public String updateCustomer(Customer customer, String[] params) {
		customerDao.update(customer, params);
		return "Customer Id is updated: "+customer.getCustomerId();
	}
}