package com.infy.ekart.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EK_CARD")
public class Card {
	
	@Id
	@Column(name="CARD_ID")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer cardID;
	
	@Column(name="CARD_TYPE")
	private String cardType;
	
	@Column(name="CARD_NUMBER")
	private String cardNumber;
	
	@Column(name="CVV")
	private String cvv;
	
	@Column(name="EXPIRY_DATE")
	private LocalDate expiryDate;
	
	@Column(name="NAME_ON_CARD")
	private String nameOnCard;
	
	@Column(name="CUSTOMER_EMAIL_ID")
	private String customerEmailId;
	

	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public Integer getCardID() {
		return cardID;
	}
	public String getCustomerEmailId() {
		return customerEmailId;
	}
	public void setCustomerEmailId(String customerEmailId) {
		this.customerEmailId = customerEmailId;
	}
	public void setCardId(Integer cardID) {
		this.cardID = cardID;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getNameOnCard() {
		return nameOnCard;
	}
	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}
	public void setCardID(Integer cardID) {
		this.cardID = cardID;
	}
	
	
}
