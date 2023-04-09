package com.example.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.model.Product;

@Repository
public interface ProductDao  extends JpaRepository<Product, Long>{

	@Query("SELECT p FROM Product p WHERE " +
			"p.productName LIKE LOWER(CONCAT('%', :productName, '%'))")
	List<Product> findByProductName(@Param("productName") String productName);
	
	@Query("SELECT p FROM Product p WHERE " +
			"p.brand LIKE LOWER(CONCAT('%', :brand, '%'))")
	Page<Product> findAllByProductBrand(@Param("brand") String brand, Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE " +
			"p.brand LIKE LOWER(CONCAT('%', :brand, '%'))" +
			" AND p.productName LIKE LOWER(CONCAT('%', :productName, '%'))")
	List<Product> findAllByProductNameAndBrand(@Param("productName") String productName, @Param("brand") String brand );
}
