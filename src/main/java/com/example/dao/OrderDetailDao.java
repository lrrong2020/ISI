package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.model.Customer;
import com.example.model.OrderDetail;
import com.example.model.OrderDetailId;
import com.example.model.Product;
import com.example.model.PurchaseOrder;
import com.example.model.Shoppingcart;

@Repository
public interface OrderDetailDao extends JpaRepository<OrderDetail, OrderDetailId>{
	@Query("SELECT od FROM OrderDetail od WHERE od.order.purchaseOrderNumber = :purchaseOrderNumber")
    List<OrderDetail> findByPurchaseOrderNumber(@Param("purchaseOrderNumber") long purchaseOrderNumber);
	
	
	//void deleteByPurchaseOrderNumber(PurchaseOrder purchaseOrder);

}
