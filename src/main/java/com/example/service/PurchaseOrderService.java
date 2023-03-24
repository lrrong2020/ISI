package com.example.service;

import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.PurchaseOrderDao;
import com.example.model.Customer;
import com.example.model.Product;
import com.example.model.PurchaseOrder;

@Service
public class PurchaseOrderService {
	@Autowired
	private PurchaseOrderDao dao; 
	
	public PurchaseOrder addPurchaseOrder(PurchaseOrder order) {
		return dao.save(order);
	}
	
	public PurchaseOrder getPurchaseOrder(long orderId) {
		PurchaseOrder order = dao.findById(orderId).orElse(null);
	    if (order != null) {
	        Hibernate.initialize(order);
	    }
	    System.out.println("Datteeeeeeeeeeeee:" + order.getShipmentDate());
	    
		return order;
	}
	
	public List<PurchaseOrder> getAllPurchaseOrder(Customer customer) {
		return dao.findByCustomer(customer);
	}
	
	public List<PurchaseOrder> getAllPurchaseOrder() {
		return dao.findAll();
	}
	
	public PurchaseOrder updatePurchaseOrder(long orderId, PurchaseOrder order) {
		PurchaseOrder existOrder = dao.findById(orderId).orElse(null);
	    if (existOrder != null) {
	        Hibernate.initialize(existOrder);
	    }
	    //if(order.getPurchaseDate()!=null)
	    	//existOrder.setPurchaseDate(order.getPurchaseDate());
	    if(order.getStatus()!=null)
	    	existOrder.setStatus(order.getStatus());
	    if(order.getTotalAmount()!=existOrder.getTotalAmount())
	    	existOrder.setTotalAmount(order.getTotalAmount());
	    if(order.getShipmentDate()!=null && existOrder.getStatus()=="shipped")
	    	existOrder.setShipmentDate(new Date(order.getShipmentDate()));
	    if(order.getCustomer()!=null) {
	    	Customer customer = order.getCustomer();
	    	//if(customer.)
	    }
	    	existOrder.setCustomer(order.getCustomer());
	    
	    return existOrder;
	}
	
	public String deletePurchaseOrder(PurchaseOrder order) {
		dao.delete(order);
		return "Purchase Order " + order.getPurchaseOrderNumber() + "is deleted successfully!";
	}

}
