package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CustomerDao;
import com.example.dao.ProductDao;
import com.example.dao.PurchaseOrderDao;
import com.example.dao.ShoppingcartDao;
import com.example.model.Customer;
import com.example.model.Product;
import com.example.model.Shoppingcart;
import com.example.model.ShoppingcartId;

@Service
public class ShoppingcartService {
	@Autowired
	private ShoppingcartDao dao; 
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private ProductDao productDao;
	
	public Shoppingcart createShoppingcart(Shoppingcart s) {
		System.out.println(s.getQuantity());
		return dao.save(s);
	}
	
	public List<Shoppingcart> getShoppingcart(int customerId) {
		return dao.findByCustomer(customerDao.getReferenceById(customerId));
	}
	
	public List<Shoppingcart> getAllShoppingcart() {
		return dao.findAll();
	}
	public void deleteProductInShoppingcart(int customerId, long productId) {
		Customer customer = customerDao.getReferenceById(customerId);
		//List<Shoppingcart> dao.findByCustomer(customerDao.getReferenceById(customerId));
		Product product = productDao.getReferenceById(productId);
		ShoppingcartId id = new ShoppingcartId(customer, product);
		dao.delete(dao.getReferenceById(id));
		System.out.println("Delete successfully!");
		//dao.deleteByProduct(productDao.getReferenceById(productId));
		//dao.findByCustomer(customerDao.getReferenceById(customerId));
	}
	public void deleteShoppingcart(int customerId) {
		dao.deleteByCustomer(customerDao.getReferenceById(customerId));
	}
	
}
