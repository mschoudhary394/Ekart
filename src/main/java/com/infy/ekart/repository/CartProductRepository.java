package com.infy.ekart.repository;



import org.springframework.data.repository.CrudRepository;

import com.infy.ekart.entity.CartProduct;




public interface CartProductRepository extends CrudRepository<CartProduct, Integer> {

}
