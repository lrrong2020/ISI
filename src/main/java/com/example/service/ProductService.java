package com.example.service;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

	public List<Product> getProductsByBrand(String brand, int page, int size) {
		System.out.println("brand: " + brand);
		
		Pageable firstPageWithTwoElements = PageRequest.of(page, size);

		List<Product> products = dao.findAllByProductBrand(brand, firstPageWithTwoElements).getContent();
		
		for(Product product: products) 
		{
			System.out.println("prodcut Name: " + product.getProductName());
		}
		
		return products;
	}

	public List<Product> getProductByNameAndBrand(String productName, String brand) {
		System.out.println("brand: " + brand);
		
		List<Product> products = dao.findAllByProductNameAndBrand(productName, brand);
		
		for(Product product: products) 
		{
			System.out.println("found in complex filtering prodcut id: " + product.getProductId());
		}
		
		return products;
	}
	
	public List<Product> getAllProductPaging(int page, int size) {
		
		Pageable firstPageWithTwoElements = PageRequest.of(page, size);
		
		Page<Product> allProducts = dao.findAll(firstPageWithTwoElements);

		List<Product> productList = allProducts.getContent();
		return productList;
	}
	
	
	//return how many pages of all products
	public int getNoOfPagesAllProducts(int page, int size) {
		
		Pageable firstPageWithTwoElements = PageRequest.of(page, size);
		
		Page<Product> allProducts = dao.findAll(firstPageWithTwoElements);

		return allProducts.getTotalPages();
	}
	
	//return how many pages of all products
	public long getNoOfAllProducts(int page, int size) {
		
		Pageable firstPageWithTwoElements = PageRequest.of(page, size);
		
		Page<Product> allProducts = dao.findAll(firstPageWithTwoElements);

		return allProducts.getTotalElements();
	}
	
	//return how many pages of all products
	public int getNoOfPagesAllProducts(String brand, int page, int size) {
		
		Pageable firstPageWithTwoElements = PageRequest.of(page, size);
		
		Page<Product> allProducts = dao.findAllByProductBrand(brand, firstPageWithTwoElements);

		return allProducts.getTotalPages();
	}
	
	//return how many pages of all products
	public long getNoOfAllProducts(String brand, int page, int size) {
		
		Pageable firstPageWithTwoElements = PageRequest.of(page, size);
		
		Page<Product> allProducts = dao.findAllByProductBrand(brand, firstPageWithTwoElements);

		return allProducts.getTotalElements();
	}
	
	//filter by product brand
}
