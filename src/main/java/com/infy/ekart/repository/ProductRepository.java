package com.infy.ekart.repository;

import org.springframework.data.repository.CrudRepository;

import com.infy.ekart.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
