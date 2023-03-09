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

import com.example.model.Customer;
import com.example.service.CustomerService;
@CrossOrigin(origins = "http://127.0.0.1:5173")
@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/add")
	public Customer addCustomer(@RequestBody Customer customer) {
		customerService.addCustomer(customer);
		return customer;
	}
	
	@GetMapping("/{customerId}")
	public Customer getCustomer(@PathVariable Integer customerId) {
		return customerService.getCustomer(customerId);
	}
	
	@GetMapping("/all")
	public List<Customer> getAllCustomer() {
		return customerService.getAllCustomers();
	}
	
	@PostMapping("/{customerId}/delete") 
	public void deleteCustomer(@PathVariable int customerId){
		Customer customer = customerService.getCustomer(customerId);
		customerService.deleteCustomer(customer);	
	}
	
	@PostMapping("/{customerId}")
	public Customer updateCustomer(@PathVariable Integer customerId, @RequestBody Customer customer) {
		return customerService.updateCustomer(customerId, customer);
	}
	
	/*@PostMapping("/customer/add")
	public Customer addCustomer(@RequestParam(value = "customerName", defaultValue = "CustomerName") String customerName) {
		Customer newCustomer = new Customer(customerName);
		System.out.println(customerName);
		customerService.addCustomer(newCustomer);
		return newCustomer;
	}
	
	
	
	@GetMapping("/customer/all")
	public List<Customer> getAllCustomer() {
		return customerService.getAllCustomers();
	}
	
	@PostMapping("/customer/{customerId}/update")
	public void updateCustomer(@PathVariable int customerId, @RequestParam(value = "customerName", defaultValue = "CustomerName") String customerName, @RequestParam(value = "customerEmail", defaultValue = "CustomerEmail") String customerEmail) {
		String[] params = {customerName, customerEmail};
		customerService.updateCustomer(customerService.getCustomer(customerId), params);
	}
	@PostMapping("/customer/{customerId}/delete") 
	public void deleteCustomer(@PathVariable int customerId){
		Customer customer = customerService.getCustomer(customerId);
		customerService.deleteCustomer(customer);	
	}
	*/
}
