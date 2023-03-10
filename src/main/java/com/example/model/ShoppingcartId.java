package com.example.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;


public class ShoppingcartId implements Serializable{
	
	private Customer customer;
	private Product product;
	
	public ShoppingcartId() {
		
	}
	
	public ShoppingcartId(Customer customer, Product product) {
		super();
		System.out.println("Constructing shopping cart");
		this.customer = customer;
		this.product = product;
	}

}
