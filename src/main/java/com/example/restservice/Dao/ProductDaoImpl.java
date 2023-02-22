package com.example.restservice.Dao;

import com.example.restservice.models.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDaoImpl implements Dao<Product> {

	private HashMap<String, Product> products = null;
	
	public ProductDaoImpl() 
	{
		products = new HashMap<String, Product>();
		products.put("A01", new Product());
		products.put("A02", new Product());
	}
	
	
	/* methods */
	
	private String generateKey() 
	{
		return "fakeKey";
	}
	
	/* setters and getters */
	@Override
	public Product get(String productId)
	{
		// TODO Auto-generated method stub
		return products.get(productId);
	}

	@Override
	public List<Product> getAll()
	{
		List<Product> productList = new ArrayList<Product>();
		for(Map.Entry<String, Product> entry : products.entrySet()) 
		{
			productList.add(entry.getValue());
		}
		
		return productList;
	}

	@Override
	public void save(Product product)
	{
		// TODO Auto-generated method stub
		
		String fakeAutoGenerateId = this.generateKey();
		
		products.put(fakeAutoGenerateId, product);
	}

	@Override
	public void update(Product product, String[] params)
	{
		// TODO Auto-generated method stub
		products.put(product.getProductId(), product);
	}
	
	@Override
	public void delete(Product product)
	{
		// TODO Auto-generated method stub
		products.remove(product.getProductId());
	}

}
