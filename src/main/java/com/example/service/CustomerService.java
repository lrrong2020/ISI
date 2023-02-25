package com.example.service;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CustomerDao;
import com.example.model.Customer;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerDao dao;
	
	public String addCustomer(Customer customer) 
	{
		dao.save(customer);
		return "Customer Added: " + customer.getCustomerId();
	}
	
	public Customer getCustomer(Integer customerId) 
	{
		 	Customer customer = dao.findById(customerId).orElse(null);
		    if (customer != null) {
		        Hibernate.initialize(customer);
		    }
		    return customer;
	}
	
	public List<Customer> getAllCustomers() {
		return dao.findAll();
	}
	
	public Customer updateCustomer(Integer customerId, Customer customer) {
		Customer existCustomer = dao.findById(customerId).orElse(null);
	    if (existCustomer != null) {
	        Hibernate.initialize(existCustomer);
	    }
	    if (customer.getCustomerName()!=null)
	    	existCustomer.setCustomerName(customer.getCustomerName());
		if (customer.getCustomerEmail()!=null)
			existCustomer.setCustomerEmail(customer.getCustomerEmail());
		if(customer.getCustomerPassword()!=null)
			existCustomer.setCustomerPassword(customer.getCustomerPassword());
		if(customer.getShippingAddress()!=null)
			existCustomer.setShippingAddress(customer.getShippingAddress());
		dao.save(existCustomer);
		return existCustomer;
	}
	
	public String deleteCustomer(Customer customer) {
		dao.delete(customer);
		return "Customer " + customer.getCustomerId() + "is deleted successfully!";
	} 
	
	
}