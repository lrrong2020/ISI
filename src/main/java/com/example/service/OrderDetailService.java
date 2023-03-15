package com.example.service;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.OrderDetailDao;
import com.example.model.OrderDetail;
import com.example.model.OrderDetailId;
import com.example.model.Product;
import com.example.model.PurchaseOrder;

@Service
public class OrderDetailService {
	@Autowired
	private OrderDetailDao dao;
	
	public OrderDetail addOrderDetail(OrderDetail detail) {
		return dao.save(detail);
	}
	
	public OrderDetail getOrderDetail(OrderDetailId id) {
		return dao.findById(id).orElseThrow();
	}
	public List<OrderDetail> getAllOrderDetail(long orderNumber) {
		return dao.findByPurchaseOrderNumber(orderNumber);
	}
	public String deleteOrderDetail(OrderDetail detail) {
		dao.delete(detail);
		return "Purchase order "+ detail.getOrder().getPurchaseOrderNumber() + "product " + detail.getProduct().getProductId()
				+ "is deleted successfully!";
	}
	
	public String addProductToOrderDetail(OrderDetail detail, Product product) {
		detail.setProduct(product);
		return "Add product "+product.getProductName() + "Successfully!";
	}
}
