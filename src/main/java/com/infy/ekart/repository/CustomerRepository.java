package com.infy.ekart.repository;



import org.springframework.data.repository.CrudRepository;

import com.infy.ekart.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, String> {

	String findByPhoneNumber(String phoneNumber);

	// add methods if required

}
