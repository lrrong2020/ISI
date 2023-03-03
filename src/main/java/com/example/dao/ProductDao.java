package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.model.Product;

@Repository
public interface ProductDao  extends JpaRepository<Product, Long>{
	
	
	@Query("SELECT p FROM Product p WHERE " +
			"p.productName LIKE :productName")
	Product findByProductName(@Param("productName") String productName);

	
	@Query("SELECT p FROM Product p WHERE " +
			"p.brand LIKE :brand")
	Product findAllByProductBrand(@Param("brand") String brand);
}
