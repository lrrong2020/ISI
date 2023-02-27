package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.OrderDetail;
import com.example.model.OrderDetailId;
import com.example.model.Product;

@Repository
public interface OrderDetailDao extends JpaRepository<OrderDetail, OrderDetailId>{

}
