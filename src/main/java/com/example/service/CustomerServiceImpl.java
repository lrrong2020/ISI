package com.example.service;

import com.example.dao.CustomerDao;
import com.example.dao.CustomerDaoImpl;
import com.example.model.Customer;

public class CustomerServiceImpl {
	
	private CustomerDao dao ;
	
	public String addCustomer(Customer customer) 
	{
		dao.save(customer);
		return "Customer Added: " + customer.getCustomerId();
	}

}
