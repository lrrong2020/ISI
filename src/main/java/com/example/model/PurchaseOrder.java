package com.example.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private String purchaseDate;
	
	private int totalAmount;
	
	private String status;
	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;
	
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private String shipmentDate;
	
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private String cancelDate;
	

	private String cancelPerson;

	public PurchaseOrder() {};
	public PurchaseOrder(long purchaseOrderNumber, String purchaseDate, int totalAmount, String status,
			Customer customer, String shipmentDate, String cancelDate, String cancelPerson) {
		this.purchaseOrderNumber = purchaseOrderNumber;
		this.purchaseDate = purchaseDate;
		this.totalAmount = totalAmount;
		this.status = status;
		this.customer = customer;
		this.shipmentDate = shipmentDate;
		this.cancelDate = cancelDate;
		this.cancelPerson = cancelPerson;
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

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		Timestamp newDate = new Timestamp(purchaseDate.getTime());
		String s = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss").format(newDate);
		this.purchaseDate = s;
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

	public String getShipmentDate() {
		return shipmentDate;
	}

	public void setShipmentDate(Date shipmentDate) {
		Timestamp newDate = new Timestamp(shipmentDate.getTime());
		String s = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss").format(newDate);
		System.out.println("SimpleDateFormat:" + s);
		this.shipmentDate = s;
	}
	
	public String getCancelDate() {
		return cancelDate;
	}
	
	public void setCancelDate(Date cancelDate) {
		Timestamp newDate = new Timestamp(cancelDate.getTime());
		String s = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss").format(newDate);
		this.cancelDate = s;
	}
	public String getCancelPerson() {
		return cancelPerson;
	}
	public void setCancelPerson(String cancelPerson) {
		this.cancelPerson = cancelPerson;
	}
	
	
	
}
