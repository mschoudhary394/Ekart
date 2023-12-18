package com.infy.ekart.service;

import java.util.List;

import com.infy.ekart.dto.ProductDTO;
import com.infy.ekart.exception.EKartException;


public interface CustomerProductService {
	List<ProductDTO> getAllProducts() throws EKartException;
	
    ProductDTO getProductById(Integer productId) throws EKartException;
	
	
    void reduceAvailableQuantity(Integer productId, Integer quantity) throws EKartException ;
}
