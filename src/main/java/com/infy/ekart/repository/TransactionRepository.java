package com.infy.ekart.repository;



import org.springframework.data.repository.CrudRepository;

import com.infy.ekart.entity.Transaction;



public interface TransactionRepository   extends CrudRepository<Transaction, Integer> {

}
