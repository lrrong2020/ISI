package com.example.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.example.model.Customer;

public class CustomerDaoImpl {

	private HashMap<Integer, Customer> customers = null;
	
	private final AtomicInteger counter = new AtomicInteger();

	public CustomerDaoImpl() {
		//customers = new HashMap<Integer, Customer>();
		//customers.put(001, new Customer());
		//customers.put(002, new Customer());
	}
   /*
	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Customer> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Customer> List<S> saveAllAndFlush(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllInBatch(Iterable<Customer> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Customer getOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getReferenceById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Customer> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Customer> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Customer> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Customer> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Customer> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Customer entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Customer> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Customer> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Customer> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Customer> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Customer> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Customer> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends Customer> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <S extends Customer, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		// TODO Auto-generated method stub
		return null;
	}

	/* methods */

	/*private Integer generateKey() {
		return counter.incrementAndGet();
	}

	/* setters and getters */
	//@Override
	/*public Customer get(Integer customerId) {
		return customers.get(customerId);
	}

	/* Dao Methods */
	/*
	@Override
	public List<Customer> getAll(){
		List<Customer> customerList = new ArrayList<Customer>();
		for (Map.Entry<Integer, Customer> entry : customers.entrySet()) {
			customerList.add(entry.getValue());
		}
		return customerList;
		
	}
	

	@Override
	public void save(Customer customer) {
		Integer fakeAutoGenerateId = this.generateKey();
		customer.setCustomerId(fakeAutoGenerateId);
		customers.put(fakeAutoGenerateId, customer);
		
		System.out.println("Customer Saved: " + customer.getCustomerId());
	}

	
	@Override
	public void update(Customer customer, String[] params) {
		customer.setCustomerName(params[0]);
		customer.setCustomerEmail(params[1]);
		customers.replace(customer.getCustomerId(), customer);
	}
//
	@Override
	public void delete(Customer customer) {
		customers.remove(customer.getCustomerId());
		System.out.println("Customer Removed: " + customer.getCustomerId());
	}
	*/

}
