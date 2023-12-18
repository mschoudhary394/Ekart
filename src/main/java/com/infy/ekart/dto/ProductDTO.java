package com.infy.ekart.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ProductDTO {
	
	private Integer productId;
	@Pattern(regexp = "([A-Za-z0-9-.])+(\\s[A-Za-z0-9-.]+)*", message = "{product.invalid.name}")
	private String name;
	@Size(min = 10, message = "{product.invalid.description}")
	private String description;
	private String category;
	@Size(min = 3, message = "{product.invalid.brand}")
	private String brand;
	@Min(value = 1, message = "{product.invalid.price}")
	private Double price;
	@Min(value = 0, message = "{product.invalid.discount}")
	@Max(value = 99, message = "{product.invalid.discount}")
	private Double discount;
	@Min(value = 1, message = "{product.invalid.quantity}")
	private Integer quantity;
	private String errorMessage;
	private String successMessage;
	private String sellerEmailId;
	private Integer availableQuantity;
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getSuccessMessage() {
		return successMessage;
	}
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
	public String getSellerEmailId() {
		return sellerEmailId;
	}
	public void setSellerEmailId(String sellerEmailId) {
		this.sellerEmailId = sellerEmailId;
	}
	public Integer getAvailableQuantity() {
		return availableQuantity;
	}
	public void setAvailableQuantity(Integer availableQuantity) {
		this.availableQuantity = availableQuantity;
	}
	
	


}
