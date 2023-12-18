package com.infy.ekart.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CardDTO {
	@Override
	public String toString() {
		return "CardDTO [cardType=" + cardType + ", cardNumber=" + cardNumber + ", nameOnCard=" + nameOnCard
				+ ", hashCvv=" + hashCvv + ", cvv=" + cvv + ", expiryDate=" + expiryDate + ", cardId=" + cardId
				+ ", customerEmailId=" + customerEmailId + "]";
	}
	private String cardType;
	private String cardNumber;
	private String nameOnCard;
	private String hashCvv;
	@NotNull(message = "{transaction.cvv.notpresent}")
	private Integer cvv;
	private LocalDate expiryDate;
	@NotNull(message = "{transaction.cardId.notpresent}")
	private Integer cardId;
	private String customerEmailId;
	
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
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
	public String getCustomerEmailId() {
		return customerEmailId;
	}
	public void setCustomerEmailId(String customerEmailId) {
		this.customerEmailId = customerEmailId;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public Integer getCardId() {
		return cardId;
	}
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
	public String getHashCvv() {
		return hashCvv;
	}
	public void setHashCvv(String hashCvv) {
		this.hashCvv = hashCvv;
	}
	public Integer getCvv() {
		return cvv;
	}
	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}
	
}
