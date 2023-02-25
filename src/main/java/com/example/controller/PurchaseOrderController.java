package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Product;
import com.example.model.PurchaseOrder;
import com.example.service.ProductService;
import com.example.service.PurchaseOrderService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/order")
public class PurchaseOrderController {
	
	@Autowired
	private PurchaseOrderService orderService;
	
	@PostMapping("/add")
	public PurchaseOrder addPurchaseOrder(@RequestBody PurchaseOrder order) {
		return orderService.addPurchaseOrder(order);
	}
	
	@GetMapping("/{orderId}")
	public PurchaseOrder getPurchaseOrder(@PathVariable long orderId) {
		return orderService.getPurchaseOrder(orderId);
	}
	
	@GetMapping("/all")
	public List<PurchaseOrder> getAllPurchaseOrder() {
		return orderService.getAllPurchaseOrder();
	}
	
	@PostMapping("/{orderId}")
	public PurchaseOrder updatePurchaseOrder(@PathVariable long orderId, @RequestBody PurchaseOrder order) {
		return orderService.updatePurchaseOrder(orderId, order);
	}
	
	@PostMapping("/{orderId}/delete") 
	public void deletePurchaseOrder(@PathVariable long orderId){
		PurchaseOrder order = orderService.getPurchaseOrder(orderId);
		orderService.deletePurchaseOrder(order);
	}
	
	
	
	
	

}
