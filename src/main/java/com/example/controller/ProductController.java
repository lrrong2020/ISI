package com.example.controller;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
	public List<Product> getAllProductPaging(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "size", required = false) String size) {
		if(page != null && size != null) {
			return productService.getAllProductPaging(Integer.valueOf(page), Integer.valueOf(size));
		}
		return productService.getAllProduct();
	}
	
	@GetMapping("/all/noOfItems")
	public long getNoOfAllProducts(@RequestParam(value = "brand", required = false) String brand, @RequestParam(value = "page", required = true) String page, @RequestParam(value = "size", required = true) String size) {
		if(brand != null) {
			return productService.getNoOfAllProducts(brand, Integer.valueOf(page), Integer.valueOf(size));
		}
		return productService.getNoOfAllProducts(Integer.valueOf(page), Integer.valueOf(size));
	}
	
	@GetMapping("/all/noOfPages")
	public int getNoOfPages(@RequestParam(value = "brand", required = false) String brand, @RequestParam(value = "page", required = true) String page, @RequestParam(value = "size", required = true) String size) {
		if(brand != null) {
			return productService.getNoOfPagesAllProducts(brand, Integer.valueOf(page), Integer.valueOf(size));
		}
		return productService.getNoOfPagesAllProducts(Integer.valueOf(page), Integer.valueOf(size));
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
	public List<Product> getProductByProductName(@RequestParam Map<String, String> query) {
		System.out.println("query = brand " + query.containsKey("brand"));
		
		System.out.println("query = productName " + query.containsKey("productName"));
		
		System.out.println("query = na " + query.containsKey("na"));
		
		for(String q : query.keySet()) {
			System.out.println("key: " + q);
		}
		
		for(Entry<String, String> q : query.entrySet()) {
			System.out.println("value: " + q.getValue());
		}
		
		if(query.containsKey("brand")) {
			return productService.getProductByNameAndBrand(query);
		}
		else {
			return productService.getProductByName(query);
		}

	}
	
	@GetMapping("/filter")
	public List<Product> getProductsByBrand(@RequestParam("brand") String brand, @RequestParam(value = "page", required = false) String page, @RequestParam(value = "size", required = false) String size) {
		System.out.println("brand:" + brand);
		if(page == null) {
			return productService.getProductsByBrand(brand);
		}
		else {
			return productService.getProductsByBrand(brand, Integer.valueOf(page), Integer.valueOf(size));
		}
	}

}
