package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Customer;
import com.example.model.Preference;
import com.example.model.PreferenceId;
import com.example.model.Product;
import com.example.service.CustomerService;
import com.example.service.PreferenceService;
import com.example.service.ProductService;

@CrossOrigin(origins = "http://127.0.0.1:5173")
@RestController
@RequestMapping(value = "/preference")
public class PreferenceController {
	@Autowired
	private PreferenceService preferenceService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/")
	public String home() {
		return "Hello World";
	}
	
	@PostMapping("/add")
	public void addPreference(@PathVariable int customerId, @PathVariable long productId, @PathVariable boolean like) {
		Customer customer = customerService.getCustomer(customerId);
		
		Product product = productService.getProduct(productId);
		preferenceService.createPreference(new Preference(customer, product, like));
	}
	
	@PostMapping("/update")
	public void updatePreference(@PathVariable int customerId, @PathVariable long productId, @PathVariable boolean like) {
		Customer customer = customerService.getCustomer(customerId);
		
		Product product = productService.getProduct(productId);
		
		preferenceService.updatePreference(new Preference(customer, product, like));
	}
	
	@PostMapping("/get")
	public Preference getPreference(@PathVariable int customerId, @PathVariable long productId) {
		Customer customer = customerService.getCustomer(customerId);
		
		Product product = productService.getProduct(productId);
		
		return preferenceService.getPreference(new PreferenceId(customer, product));
	}
}
