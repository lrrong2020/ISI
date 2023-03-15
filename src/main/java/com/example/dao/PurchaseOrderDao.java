package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Customer;
import com.example.model.PurchaseOrder;
import com.example.model.Shoppingcart;

@Repository
public interface PurchaseOrderDao extends JpaRepository<PurchaseOrder, Long>{
	List<PurchaseOrder> findByCustomer(Customer customer);
}
