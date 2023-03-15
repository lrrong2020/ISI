package com.example.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "product_id")
	private long productId;
	
	@Nonnull
	private String productName;
	
	@Nonnull
	private int price;
	
	@Nonnull
	private String property;
	
	@Nonnull
	private String brand;
	
	@Nonnull
	private String url;
	
	

	//constructors
	public Product() {};
	public Product(long productId, String productName, int price, String property, String brand, String url) {
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.property = property;
		this.brand = brand;
		this.url = url;
		
	}
	
	//methods
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	/*public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	*/
	
	//for debug
	public String toString() {
		return "productId: " + this.getProductId() +
				"productName: " + this.getProductName() +
				"brand: " + this.getBrand() +
				"price: " + this.getPrice() +
				"property: " + this.getProperty()
				;
	}
}