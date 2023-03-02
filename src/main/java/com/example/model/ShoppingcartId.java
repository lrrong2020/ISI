package com.example.model;

import java.io.Serializable;

public class ShoppingcartId implements Serializable{
	
	private Customer customer;
	private Product product;
	
	public ShoppingcartId(Customer customer, Product product) {
		super();
		this.customer = customer;
		this.product = product;
	}

}
