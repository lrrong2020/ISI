package com.example.service;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CustomerDao;
import com.example.dao.CustomerDaoImpl;
import com.example.dao.Dao;
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
	
	//public void updateCustomer(Customer customer, String[]params) {
	//	customerDao.update(customer, params);
	//}
	
	public String deleteCustomer(Customer customer) {
		dao.delete(customer);
		return "Customer " + customer.getCustomerId() + "is deleted successfully!";
	} 
	
	
}