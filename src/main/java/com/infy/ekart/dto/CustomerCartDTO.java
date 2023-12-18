package com.infy.ekart.dto;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;



public class CustomerCartDTO {
	
	private Integer cartId;
	@NotNull(message = "{customeremail.absent}")
	@Pattern(regexp = "[a-zA-Z0-9._]+@[a-zA-Z]{2,}\\.[a-zA-Z][a-zA-Z.]+" , message = "{invalid.customeremail.format}")
	private String customerEmailId;
	@Valid
	private Set<CartProductDTO> cartProducts;
	
	
	public Integer getCartId() {
		return cartId;
	}
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
	public String getCustomerEmailId() {
		return customerEmailId;
	}
	public void setCustomerEmailId(String customerEmailId) {
		this.customerEmailId = customerEmailId;
	}
	public Set<CartProductDTO> getCartProducts() {
		return cartProducts;
	}
	public void setCartProducts(Set<CartProductDTO> cartProducts) {
		this.cartProducts = cartProducts;
	}
	
	
	
	
	

}
