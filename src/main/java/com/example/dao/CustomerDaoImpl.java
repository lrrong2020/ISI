package com.example.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import com.example.model.Customer;

public class CustomerDaoImpl implements Dao<Customer> {

	private HashMap<Integer, Customer> customers = null;
	
	private final AtomicInteger counter = new AtomicInteger();

	public CustomerDaoImpl() {
		customers = new HashMap<Integer, Customer>();
//		customers.put(001, new Customer());
//		customers.put(002, new Customer());
	}

	/* methods */

	private Integer generateKey() {
		return counter.incrementAndGet();
	}

	/* setters and getters */
	@Override
	public Customer get(Integer customerId) {
		return customers.get(customerId);
	}

	/* Dao Methods */

	@Override
	public List<Customer> getAll(){
		List<Customer> customerList = new ArrayList<Customer>();
		for (Map.Entry<Integer, Customer> entry : customers.entrySet()) {
			customerList.add(entry.getValue());
		}
		return customerList;
		
	}
	

	@Override
	public void save(Customer customer) {
		Integer fakeAutoGenerateId = this.generateKey();
		customer.setCustomerId(fakeAutoGenerateId);
		customers.put(fakeAutoGenerateId, customer);
		
		System.out.println("Customer Saved: " + customer.getCustomerId());
	}

	
	@Override
	public void update(Customer customer, String[] params) {
		customer.setCustomerName(params[0]);
		customer.setCustomerEmail(params[1]);
		customers.replace(customer.getCustomerId(), customer);
	}
//
	@Override
	public void delete(Customer customer) {
		customers.remove(customer.getCustomerId());
		System.out.println("Customer Removed: " + customer.getCustomerId());
	}

}
