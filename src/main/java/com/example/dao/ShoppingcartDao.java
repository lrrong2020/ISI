package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Customer;
import com.example.model.OrderDetail;
import com.example.model.OrderDetailId;
import com.example.model.Product;
import com.example.model.Shoppingcart;
import com.example.model.ShoppingcartId;

public interface ShoppingcartDao extends JpaRepository<Shoppingcart, ShoppingcartId>{
	List<Shoppingcart> findByCustomer(Customer customer);
	
	
    void deleteByCustomer(Customer customer);
    
    //void deleteByProduct(Product product);
}
