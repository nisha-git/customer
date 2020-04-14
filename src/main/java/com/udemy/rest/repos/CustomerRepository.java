package com.udemy.rest.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udemy.rest.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
