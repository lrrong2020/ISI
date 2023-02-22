package com.example.restservice.Dao;

import com.example.restservice.models.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDaoImpl implements Dao<Product> {

	private HashMap<Integer, Product> products = null;
	
	public ProductDaoImpl() 
	{
		products = new HashMap<Integer, Product>();
		products.put(2001, new Product());
		products.put(2002, new Product());
	}
	
	
	/* methods */
	
	private Integer generateKey() 
	{
		return 0;
	}
	
	/* setters and getters */
	@Override
	public Product get(Integer productId)
	{
		// TODO Auto-generated method stub
		return products.get(productId);
	}

	@Override
	public List<Product> getAll()
	{
		List<Product> productList = new ArrayList<Product>();
		for(Map.Entry<Integer, Product> entry : products.entrySet()) 
		{
			productList.add(entry.getValue());
		}
		
		return productList;
	}

	@Override
	public void save(Product product)
	{
		// TODO Auto-generated method stub
		
		Integer fakeAutoGenerateId = this.generateKey();
		
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
