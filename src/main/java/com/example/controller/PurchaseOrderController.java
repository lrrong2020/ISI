package com.example.controller;

import java.util.Date;
import java.util.List;

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
import com.example.model.PurchaseOrder;
import com.example.service.CustomerService;
import com.example.service.ProductService;
import com.example.service.PurchaseOrderService;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@CrossOrigin(origins = "http://127.0.0.1:5173")
@RestController
@RequestMapping("customer/{customerId}/order")
public class PurchaseOrderController {
	
	@Autowired
	private PurchaseOrderService orderService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private OrderDetailController orderDetailController;
	
	@PostMapping("/add")
	public PurchaseOrder addPurchaseOrder(@RequestBody PurchaseOrder order) {
		return orderService.addPurchaseOrder(order);
	}
	
	@GetMapping("/{orderId}")
	public PurchaseOrder getPurchaseOrder(@PathVariable long orderId) {
		return orderService.getPurchaseOrder(orderId);
	}
	
	@GetMapping("/all")
	public List<PurchaseOrder> getAllPurchaseOrder(@PathVariable int customerId) {
		Customer customer = customerService.getCustomer(customerId);
		return orderService.getAllPurchaseOrder(customer);
	}
	
	@Transactional
	@PostMapping("/{orderId}")
	public PurchaseOrder updatePurchaseOrder(@PathVariable long orderId, @RequestBody PurchaseOrder order) {
		PurchaseOrder existOrder = orderService.getPurchaseOrder(orderId);
		if(order.getCustomer()!=null) {
			Customer customer = order.getCustomer();
			int customerId = customer.getCustomerId();
			existOrder.setCustomer(customerService.getCustomer(customerId));
		}
		if(order.getPurchaseDate()!=null)
			existOrder.setPurchaseDate(order.getPurchaseDate());
		if(order.getShipmentDate()!=null)
			existOrder.setShipmentDate(order.getShipmentDate());
		if(order.getStatus()!=null) {
			String status = order.getStatus();
			existOrder.setStatus(status); 
			if (status=="cancelled") {
				existOrder.setCancelDate(new Date());
				existOrder.setCancelPerson(order.getCancelPerson());
			}
		}
		if(order.getTotalAmount()!=0)
			existOrder.setTotalAmount(order.getTotalAmount());
		entityManager.persist(existOrder);
		return existOrder;
	}
	
	@PostMapping("/{orderId}/delete") 
	public void deletePurchaseOrder(@PathVariable long orderId){
		PurchaseOrder order = orderService.getPurchaseOrder(orderId);
		orderDetailController.deleteOrderDetail(orderId);
		//orderService.deletePurchaseOrder(order);
	}
	
	
	
	
	

}
