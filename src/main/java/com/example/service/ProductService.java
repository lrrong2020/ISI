package com.example.service;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dao.ProductDao;

import com.example.model.Product;

@Service
public class ProductService {

	@Autowired
	private ProductDao dao;

	public Product addProduct(Product product) {
		return dao.save(product);
	}

	public Product getProduct(long productId) {
		Product product = dao.findById(productId).orElse(null);
		if (product != null) {
			Hibernate.initialize(product);
		}
		return product;
	}

	public List<Product> getAllProduct() {
		return dao.findAll();
	}

	public Product updateProduct(long productId, Product product) {
		Product existProduct = dao.findById(productId).orElse(null);
		if (existProduct != null) {
			Hibernate.initialize(existProduct);
		}
		
		if (product.getProductName()!=null) {
			existProduct.setProductName(product.getProductName());
		}
		
		if (product.getBrand()!=null) {
			existProduct.setBrand(product.getBrand());
		}
		
		if(product.getPrice()!=0) {
			existProduct.setPrice(product.getPrice());
		}

		if(product.getProperty()!=null) {
			existProduct.setProperty(product.getProperty());
		}
		
		if(product.getPropertySecond()!=null) {
			existProduct.setPropertySecond(product.getPropertySecond());
		}
		
		if(product.getPhoto()!=null) {
			existProduct.setPhoto(product.getPhoto());
		}

		
		dao.save(existProduct);
		return existProduct;
	}

	public String deleteProduct(Product product) {
		dao.delete(product);
		return "Product " + product.getProductId() + "is deleted successfully!";
	} 
	
	
	/* advanced functions */
	
	//search by product name
	
	public List<Product> getProductByName(String productNameQuery) {
		System.out.println("Searching for: " + productNameQuery);
		
		List<Product> productResList = dao.findByProductName(productNameQuery);
				
		return productResList;
	}

	public List<Product> getProductsByBrand(String brand) {
		System.out.println("brand: " + brand);
		
		List<Product> products = dao.findAllByProductBrand(brand);
		
		for(Product product: products) 
		{
			System.out.println("prodcut Name: " + product.getProductName());
		}
		
		return products;
	}
	
	
	//filter by product brand
}
