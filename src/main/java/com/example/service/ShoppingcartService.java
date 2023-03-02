package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.PurchaseOrderDao;
import com.example.dao.ShoppingcartDao;
import com.example.model.Shoppingcart;
import com.example.model.ShoppingcartId;

@Service
public class ShoppingcartService {
	@Autowired
	private ShoppingcartDao dao; 
	
	public Shoppingcart createShoppingcart(Shoppingcart s) {
		return dao.save(s);
	}
	
	public Shoppingcart getShoppingcart(ShoppingcartId id) {
		return dao.getById(id);
	}
	
	public List<Shoppingcart> getAllShoppingcart() {
		return dao.findAll();
	}
	
	public void deleteShoppingcart(Shoppingcart s) {
		dao.delete(s);
	}
	
}
