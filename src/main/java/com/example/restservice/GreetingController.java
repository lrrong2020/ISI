package com.example.restservice;

import com.example.restservice.Dao.CustomerDao;
import com.example.restservice.models.Customer;
import com.example.restservice.services.CustomerService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customer")
	public List<Customer> getAllCustomers(){
		return customerService.getAllCustomers();
	}
	@GetMapping("/customer/:id")
	public Customer getCustomer(@RequestBody int id) {
		return customerService.getCustomer(id);
	}
	@PostMapping("/customer/add")
	public String addCustomer(@RequestBody Customer customer) {
		return customerService.addCustomer(customer);
	}
	
    
    
	
	
}