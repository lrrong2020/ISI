package com.example.restservice.Dao;

import com.example.restservice.models.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerDaoImpl implements Dao<Customer> {

	private HashMap<String, Customer> customers = null;
	
	public CustomerDaoImpl() 
	{
		customers = new HashMap<String, Customer>();
		customers.put("A01", new Customer());
		customers.put("A02", new Customer());
	}
	
	
	/* methods */
	
	private String generateKey() 
	{
		return "fakeKey";
	}
	
	/* setters and getters */
	@Override
	public Customer get(String customerId)
	{
		// TODO Auto-generated method stub
		return customers.get(customerId);
	}

	@Override
	public List<Customer> getAll()
	{
		List<Customer> customerList = new ArrayList<Customer>();
		for(Map.Entry<String, Customer> entry : customers.entrySet()) 
		{
			customerList.add(entry.getValue());
		}
		
		return customerList;
	}

	@Override
	public void save(Customer customer)
	{
		String fakeAutoGenerateId = this.generateKey();
		
		customers.put(fakeAutoGenerateId, customer);
	}

	@Override
	public void update(Customer customer, String[] params)
	{
		customers.put(customer.getCustomerId(), customer);
	}
	
	@Override
	public void delete(Customer customer)
	{
		customers.remove(customer.getCustomerId());
	}

}
