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
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Customer;
import com.example.model.Product;
import com.example.model.Shoppingcart;
import com.example.service.CustomerService;
import com.example.service.ShoppingcartService;

import jakarta.persistence.EntityManager;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/customer/{customerId}/shoppingcart")
public class ShoppingcartController {
	@Autowired
	private ShoppingcartService service;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private EntityManager entityManager;
	@PostMapping("/add")
	public void createShoppingcart(@PathVariable int customerId, @RequestBody ArrayList<Product> productList) {
		Customer customer = customerService.getCustomer(customerId);
		for(Product p: productList) {
			Shoppingcart s = new Shoppingcart(customer, p, 1,p.getPrice(), new Date());
			service.createShoppingcart(s);
		}
		
		/*for(Product p: productList) {
			Shoppingcart s = new Shoppingcart(customerId, p.getProductId());
			
		}*/
	}
	
	@GetMapping("/")
	public List<Shoppingcart> getShoppingcart(@PathVariable int customerId) {
		return service.getShoppingcart(customerId);
	}
	
	@PostMapping("/delete")
	public void deleteShoppingcart(@PathVariable int customerId) {
		service.deleteShoppingcart(customerId);
	}
	@PostMapping("/{productId}/update")
	public void updateItemOfShoppingcart(@PathVariable int customerId, @PathVariable long productId, @RequestBody int newQuantity) {
		List<Shoppingcart> records = service.getShoppingcart(customerId);
		for(Shoppingcart s: records) {
			if(s.getProduct().getProductId()==productId) {
				s.setQuantity(newQuantity);
				entityManager.persist(s);
				break;
			}
				
		}
	}
	}
