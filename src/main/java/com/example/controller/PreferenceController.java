package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/preference")
public class PreferenceController {
	@Autowired
	private PreferenceService preferenceService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/add")
	public Preference addPreference(@RequestParam(value = "customerId") int customerId, @RequestParam(value = "productId") long productId, @RequestParam(value = "like") boolean like) {
		Customer customer = customerService.getCustomer(customerId);
		
		Product product = productService.getProduct(productId);
		return preferenceService.createPreference(new Preference(customer, product, like));
	}
	
	@PostMapping("/update")
	public Preference updatePreference(@RequestParam(value = "customerId") int customerId, @RequestParam(value = "productId") long productId, @RequestParam(value = "like") boolean like) {
		Customer customer = customerService.getCustomer(customerId);
		
		Product product = productService.getProduct(productId);
		
		return preferenceService.updatePreference(new Preference(customer, product, like));
	}
	
	@GetMapping("/get")
	public Preference getPreference(@RequestParam(value = "customerId") int customerId, @RequestParam(value = "productId") long productId) {
		Customer customer = customerService.getCustomer(customerId);
		
		Product product = productService.getProduct(productId);
		
		System.out.println("getPreference in controller");
		
		return preferenceService.getPreference(new PreferenceId(customer, product));
	}
}
