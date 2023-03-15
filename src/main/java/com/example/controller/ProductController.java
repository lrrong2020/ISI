package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Product;
import com.example.service.ProductService;

@CrossOrigin(origins = "http://127.0.0.1:5173")
@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@PostMapping("/add")
	public Product addProduct(@RequestBody Product product) {
		System.out.println(product.toString());
		
		return productService.addProduct(product);
	}
	
	@GetMapping("/{productId}")
	public Product getProduct(@PathVariable long productId) {
		return productService.getProduct(productId);
	}
	
	@GetMapping("/all")
	public List<Product> getAllProduct() {
		return productService.getAllProduct();
	}
	
	@PostMapping("/{productId}")
	public Product updateCustomer(@PathVariable long productId, @RequestBody Product product) {
		return productService.updateProduct(productId, product);
	}
	
	@PostMapping("/{productId}/delete") 
	public void deleteCustomer(@PathVariable long productId){
		Product product = productService.getProduct(productId);
		productService.deleteProduct(product);	
	}
	
	@GetMapping("/search")
	public Product getProductByProductName(@RequestParam("productName") String productName) {
		System.out.println("productname" + productName);
		
		return productService.getProductByName(productName);
	}
	
	@GetMapping("/filter")
	public List<Product> getProductsByBrand(@RequestParam("brand") String brand) {
		System.out.println("brand:" + brand);
		
		return productService.getProductsByBrand(brand);
	}
	

}
