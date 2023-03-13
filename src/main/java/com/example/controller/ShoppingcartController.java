package com.example.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Customer;
import com.example.model.Product;
import com.example.model.Shoppingcart;
import com.example.service.CustomerService;
import com.example.service.ProductService;
import com.example.service.ShoppingcartService;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@CrossOrigin(origins = "http://127.0.0.1:5173")
@RestController
@RequestMapping("/customer/{customerId}/shoppingcart")
public class ShoppingcartController {
	@Autowired
	private ShoppingcartService service;
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private EntityManager entityManager;
	
//	@PostMapping("/add")
//	public void createShoppingcart(@PathVariable int customerId, @RequestBody ArrayList<Product> productList) {
//		Customer customer = customerService.getCustomer(customerId);
//		for(Product p: productList) {
//			Shoppingcart s = new Shoppingcart(customer, p, 1,p.getPrice(), new Date());
//			service.createShoppingcart(s);
//		}
//		
//		/*for(Product p: productList) {
//			Shoppingcart s = new Shoppingcart(customerId, p.getProductId());
//			
//		}*/
//	}
	@PostMapping("/add")
	public void createShoppingcart(@PathVariable int customerId, @RequestBody int productId) {
		
		System.out.println("productId: " + productId);
		Customer customer = customerService.getCustomer(customerId);
		
		Product product = productService.getProduct(productId);
		System.out.println(product.getBrand() + ",");
		
//		for(Product p: productList) {
//			Shoppingcart s = new Shoppingcart(customer, p, 1,p.getPrice(), new Date());
//			service.createShoppingcart(s);
//		}
		
		Shoppingcart s = new Shoppingcart(customer, product, 1, product.getPrice(), new Date());
		service.createShoppingcart(s);
		
		/*for(Product p: productList) {
			Shoppingcart s = new Shoppingcart(customerId, p.getProductId());
			
		}*/
	}
	
	@GetMapping("/")
	public List<Shoppingcart> getShoppingcart(@PathVariable int customerId) {
		return service.getShoppingcart(customerId);
	}
	
	//clear shopping cart of a certain customer
	@PostMapping("/clear")
	public void clearShoppingcart(@PathVariable int customerId) {
		service.deleteShoppingcart(customerId);
	}
	
	@Transactional
	@PostMapping("/{productId}/update")
	public void updateItemOfShoppingcart(@PathVariable int customerId, @PathVariable long productId, @RequestBody int newQuantity) {
		List<Shoppingcart> records = service.getShoppingcart(customerId);
		for(Shoppingcart s: records) {
			if(s.getProduct().getProductId() == productId) {
				//0
				if(newQuantity == 0) {
					//delete the record
					service.deleteProductInShoppingcart(customerId, productId);
					break;
				}
				
				s.setQuantity(newQuantity);
				entityManager.persist(s);
				break;
			}
		}
	}
	}
