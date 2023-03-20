package com.example.model;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class PurchaseOrder {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "purchase_order_number")
	private long purchaseOrderNumber;
	
	private Date purchaseDate;
	
	private int totalAmount;
	
	private String status;
	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;
	
	private Date shipmentDate;
	
	public PurchaseOrder() {};
	public PurchaseOrder(long purchaseOrderNumber, Date purchaseDate, int totalAmount, String status,
			Customer customer, Date shipmentDate) {
		this.purchaseOrderNumber = purchaseOrderNumber;
		this.purchaseDate = purchaseDate;
		this.totalAmount = totalAmount;
		this.status = status;
		this.customer = customer;
		this.shipmentDate = shipmentDate;
		
	}

	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public long getPurchaseOrderNumber() {
		return purchaseOrderNumber;
	}

	public void setPurchaseOrderNumber(long purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getShipmentDate() {
		return shipmentDate;
	}

	public void setShipmentDate(Date shipmentDate) {
		this.shipmentDate = shipmentDate;
	}
	
	
	
}
