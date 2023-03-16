package com.example.model;

import org.springframework.stereotype.Repository;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@IdClass(OrderDetailId.class)
public class OrderDetail {
	@Id
	@ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "purchase_order_number", referencedColumnName = "purchase_order_number")
    private PurchaseOrder order;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;
    
    private int quantity;
	
	private int totalPrice;
	
	public OrderDetail() {
		super();
	}
	
	public OrderDetail(PurchaseOrder order, Product product, int quantity, int totalPrice) {
		super();
		this.order = order;
		this.product = product;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}

	public PurchaseOrder getOrder() {
		return order;
	}

	public void setOrder(PurchaseOrder order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	
	
	
}
