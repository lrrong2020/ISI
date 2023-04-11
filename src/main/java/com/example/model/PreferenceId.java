package com.example.model;

import java.io.Serializable;

public class PreferenceId implements Serializable{
	private Customer customer;
	private Product product;
	
	public PreferenceId() {
		
	}
	
	public PreferenceId(Customer customer, Product product) {
		super();
		this.customer = customer;
		this.product = product;
	}

	public Customer getCustomer() {
		// TODO Auto-generated method stub
		return this.customer;
	}

	public Product getProduct() {
		// TODO Auto-generated method stub
		return this.product;
	}
}
