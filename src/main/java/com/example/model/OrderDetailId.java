package com.example.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;


public class OrderDetailId implements Serializable {

    private PurchaseOrder order;
    private Product product;
    
    public OrderDetailId() {
    	super();
    }
	public OrderDetailId(PurchaseOrder order, Product product) {
		super();
		this.order = order;
		this.product = product;
	}
    
    
  
}
