package com.example.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CustomerDao;
import com.example.dao.PreferenceDao;
import com.example.dao.ProductDao;
import com.example.model.Customer;
import com.example.model.Preference;
import com.example.model.PreferenceId;
import com.example.model.Product;
import com.example.tool.RecommendEngine;

@Service
public class PreferenceService {
	
	@Autowired
	private PreferenceDao dao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private ProductDao productDao;
	
	
	public Preference createPreference(Preference preference) {
		return dao.save(preference);
	}
	
	public Preference getPreference(PreferenceId preferenceId) {
		System.out.println("getPreference in service");
		Customer customer = customerDao.getReferenceById(preferenceId.getCustomer().getCustomerId());
		Product product = productDao.getReferenceById(preferenceId.getProduct().getProductId());
		PreferenceId id = new PreferenceId(customer, product);
		System.out.println("id: " + id);
		
		Preference res = dao.findById(id).orElse(null);
		
		System.out.println("res: " + res);
		
		if(res == null) {
			System.out.println("Null preference");
			return new Preference();
		}
		else {
			return res;
		}
	}
	
	public Preference updatePreference(Preference preference) {
		Preference existPreference = dao.findById(new PreferenceId(preference.getCustomer(), preference.getProduct())).orElse(null);
		
		existPreference.setLike(preference.isLike());
		dao.save(existPreference);
		return existPreference;
	}
	
	//Top 3 preferred 
	public List<Product> top3PreferredProducts(Customer customer){
		List<Product> allProducts = productDao.findAll();
		List<Product> resultProducts = sortProductListPreference(allProducts, customer);
		
		return resultProducts;
	}
	
	
	//sort a product list according to the customer's likelihood of preference on them
	public List<Product> sortProductListPreference(List<Product> products, Customer customer){
		List<Product> resProducts = new ArrayList<Product>();
		
		resProducts.addAll(products);
		
		resProducts.sort((a, b) -> Double.compare(rate(a, customer), rate(b, customer)));
		return resProducts;
	}
	
	public double similarity(Customer c1, Customer c2) {
		System.out.println("Comparing " + "customer[" + c1.getCustomerId() + "] with [" + c2.getCustomerId() + "]");
		
		Set<Product> c1Like = getAllProductACustomerLikes(c1);
		Set<Product> c2Like = getAllProductACustomerLikes(c2);
		
		Set<Product> c1Dislike = getAllProductACustomerDislikes(c1);
		Set<Product> c2Dislike = getAllProductACustomerDislikes(c2);
		
		Set<Product> term1 = new HashSet<Product>();
		Set<Product> term2 = new HashSet<Product>();
		Set<Product> term3 = new HashSet<Product>();
		Set<Product> term4 = new HashSet<Product>();
		Set<Product> term5 = new HashSet<Product>();
		
		term1.addAll(c1Like);//L1
		term1.retainAll(c2Like);//intersect L2
		
		term2.addAll(c1Dislike);//D1
		term2.retainAll(c2Dislike);//intersect D2

		term3.addAll(c1Like);//L1
		term3.retainAll(c2Dislike);//intersect D2
		
		term4.addAll(c2Like);//L2
		term4.retainAll(c1Dislike);//intersect D1
		
		term5.addAll(c1Like);
		term5.addAll(c2Like);
		term5.addAll(c1Dislike);
		term5.addAll(c2Dislike);
		
		double similarity = (double)(term1.size() + term2.size() - term3.size() - term4.size()) / (double)(term5.size());
		
		System.out.println("Similarity: " + similarity);
		
		return similarity;
	}
	
	public double rate(Product product, Customer customer) {
		Set<Customer> customersLike = getAllCustomersLike(product);
		Set<Customer> customersDislike = getAllCustomersDislike(product);
		
		double sumOfSimilarityLike = 0;
		for(Customer c : customersLike) {
			sumOfSimilarityLike += similarity(customer, c);
		}
		
		double sumOfSimilarityDislike = 0;
		for(Customer c : customersDislike) {
			sumOfSimilarityDislike += similarity(customer, c);
		}
		
		System.out.println();
		System.out.println(sumOfSimilarityLike);
		System.out.println(sumOfSimilarityDislike);
		System.out.println(customersLike.size());
		System.out.println(customersDislike.size());
		
		double rate = (sumOfSimilarityLike - sumOfSimilarityDislike) / (double)(customersLike.size() + customersDislike.size());
		
		System.out.println("Rate of Customer[" + customer.getCustomerId() + "] on Product[" + product.getProductId() + "] is: " + rate);
		
		return rate;
	}
	
	public Set<Customer> getAllCustomersLike(Product product){
		Set<Customer> customers = new HashSet<Customer>();
		List<Preference> preferences = dao.findAll();
		for(Preference p : preferences) {
			if(p.getProduct().getProductId() == product.getProductId()) {
				System.out.println("p.isLike: " + p.isLike());
				if(p.isLike()) {//like
					System.out.println("Product[" + product.getProductId() + "] is not liked by Customer[" + p.getCustomer().getCustomerId() + "]");
					customers.add(p.getCustomer());
				}
				else {
					continue;
				}
			}
			else {
				continue;
			}
		}
		return customers;
	}
	
	public Set<Customer> getAllCustomersDislike(Product product){
		Set<Customer> customers = new HashSet<Customer>();
		List<Preference> preferences = dao.findAll();
		for(Preference p : preferences) {
			if(p.getProduct().getProductId() == product.getProductId()) {
				System.out.println("p.isLike: " + p.isLike());
				if(!p.isLike()) {//dislike
					System.out.println("Product[" + product.getProductId() + "] is not liked by Customer[" + p.getCustomer().getCustomerId() + "]");
					customers.add(p.getCustomer());
				}
				else {
					continue;
				}
			}
			else {
				continue;
			}
		}
		return customers;
	}
	
	
	public Set<Product> getAllProductACustomerLikes(Customer customer){
		Set<Product> products = new HashSet<Product>();
		List<Preference> preferences = dao.findAll();
		for (Preference p : preferences) {
			if (p.getCustomer().getCustomerId() ==customer.getCustomerId()) {
				if(p.isLike()) {//like
					products.add(p.getProduct());
				}
				else {
					continue;
				}
			}
			else {
				continue;
			}
		}
		
		return products;
	}
	
	public Set<Product> getAllProductACustomerDislikes(Customer customer){
		Set<Product> products = new HashSet<Product>();
		List<Preference> preferences = dao.findAll();
		for (Preference p : preferences) {
			if (p.getCustomer().getCustomerId() ==customer.getCustomerId()) {
				if(!p.isLike()) {//dislike
					products.add(p.getProduct());
				}
				else {
					continue;
				}
			}
			else {
				continue;
			}
		}
		
		return products;
	}
}
