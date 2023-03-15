package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.model.OrderDetail;
import com.example.model.OrderDetailId;
import com.example.model.Product;
import com.example.model.PurchaseOrder;
import com.example.model.Shoppingcart;
import com.example.service.OrderDetailService;
import com.example.service.ProductService;
import com.example.service.PurchaseOrderService;

@CrossOrigin(origins = "http://127.0.0.1:5173")
@RestController
@RequestMapping("customer/{customerId}/order/{orderId}")
public class OrderDetailController {
	@Autowired
	private OrderDetailService service;
	@Autowired
	private ProductService productService;
	@Autowired
	private PurchaseOrderService orderService;
	
	
	
	@GetMapping("/detail")
	public List<OrderDetail> getOrderDetail(@PathVariable long orderId) {
		return service.getAllOrderDetail(orderId);
	}
	@GetMapping("/detail/products")
	public List<Product> getProductOfOneOrder(@PathVariable long orderId) {
		List<OrderDetail> details = getOrderDetail(orderId);
		List<Product> products = new ArrayList<>();
		for(OrderDetail detail:details) {
			products.add(detail.getProduct());
		}
		return products;
	}
	@PostMapping("/detail/add")
	public void addOrderDetail(@PathVariable long orderId,@RequestBody Shoppingcart cart) {
		int sum = 0;
		Product product = cart.getProduct();
		PurchaseOrder order = orderService.getPurchaseOrder(orderId);
		OrderDetail orderDetail = new OrderDetail(order, product, cart.getQuantity(),cart.getUnitPrice()*cart.getQuantity());
		sum += cart.getQuantity();
		//order.setTotalAmount(sum);
		service.addOrderDetail(orderDetail);
		
	}
	@PostMapping("/detail/delete")
	public void deleteOrderDetail(@PathVariable long orderId) {
		service.deleteOrderDetailById(orderId);
	}
	@PutMapping("/detail/{productId}/update")
	public OrderDetail updateOrderDetail(@PathVariable long orderId, @PathVariable long pid, @RequestBody OrderDetail detailRequest) {
		Product product = productService.getProduct(pid);
		PurchaseOrder order = orderService.getPurchaseOrder(orderId);
		OrderDetailId id = new OrderDetailId(order, product);
		service.getOrderDetail(id).setQuantity(detailRequest.getQuantity());
		service.getOrderDetail(id).setTotalPrice(detailRequest.getTotalPrice());
		return service.getOrderDetail(id);
	}
	
	@PostMapping("/detail/{productId}/delete")
	public String updateOrderDetail(@PathVariable long orderId, @PathVariable long pid) {
		Product product = productService.getProduct(pid);
		PurchaseOrder order = orderService.getPurchaseOrder(orderId);
		OrderDetailId id = new OrderDetailId(order, product);
		return service.deleteOrderDetail(service.getOrderDetail(id));
		
	}
	
	
}
