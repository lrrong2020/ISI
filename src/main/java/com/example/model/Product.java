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
	private String propertySecond;
	
	@Nonnull
	private String brand;
	
	@Nonnull
	private String url;
	
	@Column(nullable = true)
    private String photo;
	


	//constructors
	public Product() {};

	public Product(long productId, String productName, int price, String property, String brand, String propertySecond, String photo) {
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.property = property;
		this.propertySecond = propertySecond;
		this.brand = brand;
		this.photo = photo;
		
		System.out.println("New Product");
		System.out.println(photo.toString());
	}
	
	//methods
	public String getPropertySecond() {
		return propertySecond;
	}
	public void setPropertySecond(String propertySecond) {
		this.propertySecond = propertySecond;
	}
	
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

	
	/*public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	*/
	
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	//for debug
	public String toString() {
		return "productId: " + this.getProductId() +
				"productName: " + this.getProductName() +
				"brand: " + this.getBrand() +
				"price: " + this.getPrice() +
				"property: " + this.getProperty() +
				"property: " + this.getPropertySecond() +
				"photo: " + this.getPhoto()
				;
	}
}