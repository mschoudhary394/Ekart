package com.infy.ekart.repository;

import org.springframework.data.repository.CrudRepository;

import com.infy.ekart.entity.Order;

public interface CustomerOrderRepository extends CrudRepository<Order, Integer> {
	// add methods if required
}
