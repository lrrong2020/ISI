package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.OrderDetail;
import com.example.model.OrderDetailId;
import com.example.model.Shoppingcart;
import com.example.model.ShoppingcartId;

public interface ShoppingcartDao extends JpaRepository<Shoppingcart, ShoppingcartId>{

}
