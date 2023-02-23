package com.example.restservice;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import com.example.restservice.Dao.CustomerDaoImpl;
import com.example.restservice.services.CustomerService;

@Configuration
@ComponentScan(basePackages = {"com.example.restservice"})
@EnableJpaRepositories(
		basePackages = "com.example.restservice.Dao",
		entityManagerFactoryRef = "entityManagerFactory"
		)
@EntityScan(basePackages = {"com.example.restservice.models"})
public class AppConfig {
	
	@Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder, DataSource dataSource, 
            JpaVendorAdapter jpaVendorAdapter) {
		return builder
	            .dataSource(dataSource)
	            .packages("com.example.restservice.models")
	            .build();
    }
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
	    return new HibernateJpaVendorAdapter();
	}

	@Bean
	public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
	    return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
	}

	@Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:sqlite:/D:\\2022-2023\\Some data\\demo\\demo\\src\\main\\resources/customer.db")
                .driverClassName("org.sqlite.JDBC")
                .build();
    }
	
    
}

