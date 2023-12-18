package com.infy.ekart.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.infy.ekart.entity.CustomerCart;

public interface CustomerCartRepository extends CrudRepository<CustomerCart, Integer> {

	Optional<CustomerCart> findByCustomerEmailId(String customerEmailId);

	// add methods if required

}
