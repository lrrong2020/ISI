package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.ShoppingcartService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/customer/{customerId}/shoppingcart")
public class ShoppingcartController {
	@Autowired
	private ShoppingcartService service;
	
	/*
	@GetMapping("/")
	public Shoppingcart getShoppingcart(@PathVariable int customerId) {
		return service.getShoppingcart(null)
	}
	*/
}
