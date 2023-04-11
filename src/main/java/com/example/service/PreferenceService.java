package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CustomerDao;
import com.example.dao.PreferenceDao;
import com.example.dao.ProductDao;
import com.example.model.Customer;
import com.example.model.Preference;
import com.example.model.PreferenceId;
import com.example.model.Product;

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
}
