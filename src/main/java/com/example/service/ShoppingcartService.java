package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CustomerDao;
import com.example.dao.PurchaseOrderDao;
import com.example.dao.ShoppingcartDao;
import com.example.model.Shoppingcart;
import com.example.model.ShoppingcartId;

@Service
public class ShoppingcartService {
	@Autowired
	private ShoppingcartDao dao; 
	@Autowired
	private CustomerDao customerDao;
	
	public Shoppingcart createShoppingcart(Shoppingcart s) {
		return dao.save(s);
	}
	
	public List<Shoppingcart> getShoppingcart(int customerId) {
		return dao.findByCustomer(customerDao.getReferenceById(customerId));
	}
	
	public List<Shoppingcart> getAllShoppingcart() {
		return dao.findAll();
	}
	
	public void deleteShoppingcart(int customerId) {
		dao.deleteByCustomer(customerDao.getReferenceById(customerId));
	}
	
}
