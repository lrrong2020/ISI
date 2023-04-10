package com.example.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.dao.ProductDao;

import com.example.model.Product;

@Service
public class ProductService {

	@Autowired
	private ProductDao dao;

	public Product addProduct(Product product) {
		return dao.save(product);
	}

	public Product getProduct(long productId) {
		Product product = dao.findById(productId).orElse(null);
		if (product != null) {
			Hibernate.initialize(product);
		}
		return product;
	}

	public List<Product> getAllProduct() {
		return dao.findAll();
	}

	public Product updateProduct(long productId, Product product) {
		Product existProduct = dao.findById(productId).orElse(null);
		if (existProduct != null) {
			Hibernate.initialize(existProduct);
		}
		
		if (product.getProductName()!=null) {
			existProduct.setProductName(product.getProductName());
		}
		
		if (product.getBrand()!=null) {
			existProduct.setBrand(product.getBrand());
		}
		
		if(product.getPrice()!=0) {
			existProduct.setPrice(product.getPrice());
		}

		if(product.getProperty()!=null) {
			existProduct.setProperty(product.getProperty());
		}
		
		if(product.getPropertySecond()!=null) {
			existProduct.setPropertySecond(product.getPropertySecond());
		}
		
		if(product.getPhoto()!=null) {
			existProduct.setPhoto(product.getPhoto());
		}

		
		dao.save(existProduct);
		return existProduct;
	}

	public String deleteProduct(Product product) {
		dao.delete(product);
		return "Product " + product.getProductId() + "is deleted successfully!";
	} 
	
	
	/* advanced functions */
	
	//search by product name
	
	public List<Product> getProductByName(Map<String, String> query) {
		String[] productNames = query.values().toArray(new String[0]);
		
		for(int i = 0; i < productNames.length; ++i) {
			System.out.println("productName [" + i + "] " + productNames[i]);
		}
		
		//search all of them at once
		
		//return the first non-empty result
		
		List<Product> productResList = new ArrayList<Product>();
		
		List<String> unUsedKeywords = new ArrayList<String>();
		
		for(int i = 0; i < productNames.length; ++i) {
			String productNameRemovedQuotes = productNames[i].substring(1, productNames[i].length() - 1);
			System.out.println("Searching for: " + productNameRemovedQuotes);
			
			List<Product> tempList = dao.findByProductName(productNames[i].substring(1, productNames[i].length() - 1));//get rid of double quotation mark
			
			for(Product p : tempList) {
				System.out.println("In tempList");
				System.out.println(p.getProductName());
			}
			
			if(tempList.size() == 0) {
				unUsedKeywords.add(productNames[i]);
				System.out.println("Unused: " + productNames[i]);
				continue;
			}
			
			if(productResList.size() == 0) {
				productResList.addAll(tempList);
			}
			else {
				productResList.retainAll(tempList);
			}
		}
		
		if(productResList.size() < 3) {
			//deal with unused keywords
			List<String> allProductNames = dao.findAll().stream().map(x -> x.getProductName()).toList();
			
			//might be slow
			productResList.addAll(fuzzySearch(unUsedKeywords, allProductNames));
		}
				
		return productResList;
	}
	
	//fuzzy search
	public List<Product> fuzzySearch(List<String> unUsedKeywords, List<String> allProductNames){
		List<String> realProductNames = new ArrayList<String>();
		List<Product> products = new ArrayList<Product>();
		for(String s : unUsedKeywords) {
			realProductNames.addAll(approximateMatch(allProductNames, s, 4));
		}
		
		for(String s : realProductNames) {
			products.addAll(dao.findByProductName(s));
		}
		return products;
	}
	
	static int levenshteinDistance(String x, String y) {
		System.out.println("Comparing " + x + " with " + y);
	    int[][] dp = new int[x.length() + 1][y.length() + 1];

	    for (int i = 0; i <= x.length(); i++) {
	        for (int j = 0; j <= y.length(); j++) {
	            if (i == 0) {
	                dp[i][j] = j;
	            }
	            else if (j == 0) {
	                dp[i][j] = i;
	            }
	            else {
	                dp[i][j] = min(dp[i - 1][j - 1] 
	                 + costOfSubstitution(x.charAt(i - 1), y.charAt(j - 1)), 
	                  dp[i - 1][j] + 1, 
	                  dp[i][j - 1] + 1);
	            }
	        }
	    }

	    return dp[x.length()][y.length()];
	}

    public static int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }
    
    public static int min(int... numbers) {
        return Arrays.stream(numbers)
          .min().orElse(Integer.MAX_VALUE);
    }
	
    public static List<String> approximateMatch(List<String> strings, String query, int maxDistance) {
        List<String> matches = new ArrayList<>();

        for (String s : strings) {
        	int distance = levenshteinDistance(s, query);
        	System.out.println("distance: " + distance);
            if (distance <= maxDistance) {
                matches.add(s);
            }
        }

        return matches;
    }
	
	public List<Product> getProductsByBrand(String brand, int page, int size) {
		System.out.println("brand: " + brand);
		
		Pageable firstPageWithTwoElements = PageRequest.of(page, size);

		List<Product> products = dao.findAllByProductBrand(brand, firstPageWithTwoElements).getContent();
		
		for(Product product: products) 
		{
			System.out.println("prodcut Name: " + product.getProductName());
		}
		
		return products;
	}

	public List<Product> getProductByNameAndBrand(String productName, String brand) {
		System.out.println("brand: " + brand);
		
		List<Product> products = dao.findAllByProductNameAndBrand(productName, brand);
		
		for(Product product: products) 
		{
			System.out.println("found in complex filtering prodcut id: " + product.getProductId());
		}
		
		return products;
	}
	
	public List<Product> getAllProductPaging(int page, int size) {
		
		Pageable firstPageWithTwoElements = PageRequest.of(page, size);
		
		Page<Product> allProducts = dao.findAll(firstPageWithTwoElements);

		List<Product> productList = allProducts.getContent();
		return productList;
	}
	
	
	//return how many pages of all products
	public int getNoOfPagesAllProducts(int page, int size) {
		
		Pageable firstPageWithTwoElements = PageRequest.of(page, size);
		
		Page<Product> allProducts = dao.findAll(firstPageWithTwoElements);

		return allProducts.getTotalPages();
	}
	
	//return how many pages of all products
	public long getNoOfAllProducts(int page, int size) {
		
		Pageable firstPageWithTwoElements = PageRequest.of(page, size);
		
		Page<Product> allProducts = dao.findAll(firstPageWithTwoElements);

		return allProducts.getTotalElements();
	}
	
	//return how many pages of all products
	public int getNoOfPagesAllProducts(String brand, int page, int size) {
		
		Pageable firstPageWithTwoElements = PageRequest.of(page, size);
		
		Page<Product> allProducts = dao.findAllByProductBrand(brand, firstPageWithTwoElements);

		return allProducts.getTotalPages();
	}
	
	//return how many pages of all products
	public long getNoOfAllProducts(String brand, int page, int size) {
		
		Pageable firstPageWithTwoElements = PageRequest.of(page, size);
		
		Page<Product> allProducts = dao.findAllByProductBrand(brand, firstPageWithTwoElements);

		return allProducts.getTotalElements();
	}
	
	//filter by product brand
}
