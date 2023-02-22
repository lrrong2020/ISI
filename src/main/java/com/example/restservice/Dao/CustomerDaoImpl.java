package com.example.restservice.Dao;

import com.example.restservice.models.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

public class CustomerDaoImpl implements Dao<Customer>, JpaRepository<Customer, Integer>  {

	private HashMap<Integer, Customer> customers = null;
	
	public CustomerDaoImpl() 
	{
		customers = new HashMap<Integer, Customer>();
		customers.put(001, new Customer());
		customers.put(002, new Customer());
	}
	
	
	/* methods */
	
	private Integer generateKey() 
	{
		return 0;
	}
	
	/* setters and getters */
	@Override
	public Customer get(Integer customerId)
	{
		return customers.get(customerId);
	}

	
	/* Dao Methods */
	
	@Override
	public List<Customer> getAll()
	{
		List<Customer> customerList = new ArrayList<Customer>();
		for(Map.Entry<Integer, Customer> entry : customers.entrySet()) 
		{
			customerList.add(entry.getValue());
		}
		
		return customerList;
	}

	@Override
	public void save(Customer customer)
	{
		Integer fakeAutoGenerateId = this.generateKey();
		
		customers.put(fakeAutoGenerateId, customer);
	}

	@Override
	public void update(Customer customer, String[] params)
	{
		customers.put(customer.getCustomerId(), customer);
	}
	
	@Override
	public void delete(Customer customer)
	{
		customers.remove(customer.getCustomerId());
	}

	
	/* JpaRepository Methods UNIMPLEMENTED*/

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


	@Override
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Customer> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Customer> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <S extends Customer> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}


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
	public <S extends Customer> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <S extends Customer> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
