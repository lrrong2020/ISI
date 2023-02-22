package com.example.restservice.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.restservice.Dao.Dao;
import com.example.restservice.models.Customer;

public class CustomerService{

	@Autowired
	private Dao<Customer> dao;
	
	public String addCustomer(Customer customer) {
		dao.save(customer);
		return "The new customer has been added into database, Id is " + customer.getCustomerId();
	}
	public Customer getCustomer(String customerId) {
		return (Customer) dao.get(customerId);
	}
	public List<Customer> getAllCustomers() {
		return dao.getAll();
	}
	public String deleteCustomer(Customer customer) {
		dao.delete(customer);
		return "Customer Id is deleted: " + customer.getCustomerId();
	}
	public String updateCustomer(Customer customer, String[] params) {
		dao.update(customer, params);
		return "Customer Id is updated: "+customer.getCustomerId();
	}
}