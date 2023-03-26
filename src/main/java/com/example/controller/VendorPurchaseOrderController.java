package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.PurchaseOrder;
import com.example.service.PurchaseOrderService;

@CrossOrigin(origins = "http://127.0.0.1:5173")
@RestController
@RequestMapping("order")

public class VendorPurchaseOrderController {
	
	@Autowired
	private PurchaseOrderService orderService;
	
	@GetMapping("/all")
	public List<PurchaseOrder> getAllOrders() {
		return orderService.getAllPurchaseOrder();
	}
	
	@GetMapping("/{orderId}")
	public PurchaseOrder getOrderById(@PathVariable long orderId) {
		return orderService.getOrderById(orderId);
	}

}
