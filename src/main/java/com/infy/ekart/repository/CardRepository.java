package com.infy.ekart.repository;

import org.springframework.data.repository.CrudRepository;

import com.infy.ekart.entity.Card;



public interface CardRepository extends CrudRepository<Card, Integer> {
	
	// add methods if required
}
