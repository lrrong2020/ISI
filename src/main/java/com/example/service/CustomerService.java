package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dao.CustomerDaoImpl;
import com.example.dao.Dao;
import com.example.model.Customer;

@Service
public class CustomerService {
	
	private Dao<Customer> customerDao = new CustomerDaoImpl();
	
	public String addCustomer(Customer customer) 
	{
		customerDao.save(customer);
		return "Customer Added: " + customer.getCustomerId();
	}
	
	public Customer getCustomer(int customerId) 
	{
		return customerDao.get(customerId);
	}
	
	public List<Customer> getAllCustomers() {
		return customerDao.getAll();
	}
	
	public void updateCustomer(Customer customer, String[]params) {
		customerDao.update(customer, params);
	}
	
	public String deleteCustomer(Customer customer) {
		customerDao.delete(customer);
		return "Customer " + customer.getCustomerId() + "is deleted successfully!";
	}
	
}