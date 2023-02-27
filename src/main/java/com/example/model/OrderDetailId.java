package com.example.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;


public class OrderDetailId implements Serializable {

    private PurchaseOrder order;
    private Product product;
    
	public OrderDetailId(PurchaseOrder order, Product product) {
		super();
		this.order = order;
		this.product = product;
	}
    
    
  
}
