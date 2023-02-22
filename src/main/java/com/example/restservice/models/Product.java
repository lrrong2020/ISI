package com.example.restservice.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {
	@Id
	private Integer productId;

	@Column
	private String productName;

	@Column
	private int price;

	@Column
	private String property;

	@Column
	private String brand;

	@Column
	private String url;

	public Product() {
	};

	public Product(Integer productId, String productName, int price, String property, String brand, String url) {
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.property = property;
		this.brand = brand;
		this.url = url;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
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

}
