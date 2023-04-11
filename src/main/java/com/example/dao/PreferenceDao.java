package com.example.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Customer;
import com.example.model.OrderDetail;
import com.example.model.OrderDetailId;
import com.example.model.Preference;
import com.example.model.PreferenceId;
import com.example.model.Product;
import com.example.model.Shoppingcart;
import com.example.model.ShoppingcartId;

public interface PreferenceDao extends JpaRepository<Preference, PreferenceId>{

}
