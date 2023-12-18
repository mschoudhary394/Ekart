package com.infy.ekart.dto;

import java.time.LocalDateTime;

import javax.validation.Valid;



public class TransactionDTO {
	@Override
	public String toString() {
		return "TransactionDTO [transactionId=" + transactionId + ", order=" + order + ", card=" + card
				+ ", totalPrice=" + totalPrice + ", transactionDate=" + transactionDate + ", transactionStatus="
				+ transactionStatus + "]";
	}
	private Integer transactionId;
	@Valid
	private OrderDTO order;
	@Valid
	private CardDTO card;
	private Double totalPrice;
	private LocalDateTime transactionDate;
	private TransactionStatus transactionStatus;
	
	public Integer getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}
	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	public OrderDTO getOrder() {
		return order;
	}
	public void setOrder(OrderDTO order) {
		this.order = order;
	}
	public CardDTO getCard() {
		return card;
	}
	public void setCard(CardDTO card) {
		this.card = card;
	}
	
	
}
