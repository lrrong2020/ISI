package com.example.restservice.models;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;

@Entity
public class PurchaseOrder {
	@Id
	private Integer purchaseOrderNumber;
	
	@Column
	private Date purchaseDate;
	
	@Column
	private int totalAmount;
	
	@Column
	private String status;
	
	@OneToMany(cascade = CascadeType.ALL)
	
	@JoinColumn(name = "fk_po_id")
	private String customerId;
	
	@Column
	private Date shipmentDate;

	public PurchaseOrder() {
	};

	public PurchaseOrder(Integer purchaseOrderNumber, Date purchaseDate, int totalAmount, String status,
			String customerId, Date shipmentDate) {
		this.purchaseOrderNumber = purchaseOrderNumber;
		this.purchaseDate = purchaseDate;
		this.totalAmount = totalAmount;
		this.status = status;
		this.customerId = customerId;
		this.shipmentDate = shipmentDate;
	}

	public Integer getPurchaseOrderNumber() {
		return purchaseOrderNumber;
	}

	public void setPurchaseOrderNumber(Integer purchaseOrderNumber) {
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

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Date getShipmentDate() {
		return shipmentDate;
	}

	public void setShipmentDate(Date shipmentDate) {
		this.shipmentDate = shipmentDate;
	}

}
