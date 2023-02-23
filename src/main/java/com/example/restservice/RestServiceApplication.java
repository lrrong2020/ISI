package com.example.restservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;


//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootConfiguration 
@EnableAutoConfiguration 

 
public class RestServiceApplication {
//Just for try it
	public static void main(String[] args) throws ClassNotFoundException {
		
		String url = "jdbc:sqlite:src\\main\\resources\\customer.db";
		try {
            Connection conn = DriverManager.getConnection(url);
            if (conn != null) {
                System.out.println("Connected to the database!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		SpringApplication.run(RestServiceApplication.class, args);
	}

}