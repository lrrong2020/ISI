package com.example.restservice.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restservice.models.Customer;

public interface Dao<T>{
    
    T get(Integer id);
    
    List<T> getAll();
    
    void save(T t);
    
    void update(T t, String[] params);
    
    void delete(T t);
    
}