package com.infy.ekart.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.infy.ekart.dto.CardDTO;
import com.infy.ekart.dto.TransactionDTO;
import com.infy.ekart.dto.TransactionStatus;
import com.infy.ekart.entity.Transaction;
import com.infy.ekart.exception.EKartException;
import com.infy.ekart.repository.CardRepository;
import com.infy.ekart.repository.TransactionRepository;
import com.infy.ekart.utility.HashingUtility;

//Add the missing annotation
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	// Get the list of card details by using the customerEmailId
	// If the obtained list is empty throw EKartException with message
	// PaymentService.CUSTOMER_NOT_FOUND
	// Hash the card CVV value by calling the HashingUtility.getHashValue()
	// Populate the Card details and save to database
	// Return the cardId
	@Override
	public Integer addCustomerCard(String customerEmailId, CardDTO cardDTO)
			throws EKartException, NoSuchAlgorithmException {

		// write your logic here

		return null;
	}

	// Get the card details by using the given cardId(available in CardDTO)
	// If card not obtained throw an EKartException with message
	// PaymentService.CARD_NOT_FOUND
	// Else update the given card details using setters
	@Override
	public void updateCustomerCard(CardDTO cardDTO) throws EKartException, NoSuchAlgorithmException {

		// write your logic here
	}

	// Get the list of card details by using the customerEmailId and cardId
	// If the obtained list is empty throw EKartException with message
	// PaymentService.CUSTOMER_NOT_FOUND
	// Else get the card detail and delete the same
	// If card not obtained throw an EKartException with message
	// PaymentService.CARD_NOT_FOUND
	@Override
	public void deleteCustomerCard(String customerEmailId, Integer cardId) throws EKartException {
		// write your logic here

	}

	// Get the card details by using the given cardId
	// If card not obtained throw an EKartException with message
	// PaymentService.CARD_NOT_FOUND
	// Else populate the card details and return

	@Override
	public CardDTO getCard(Integer cardId) throws EKartException {
		// write your logic here

		return null;

	}

	// Get the list of card details by using the customerEmailId and cardType
	// If the obtained list is empty throw EKartException with message
	// PaymentService.CARD_NOT_FOUND
	// Else populate the obtained card details and return
	@Override
	public List<CardDTO> getCardsOfCustomer(String customerEmailId, String cardType) throws EKartException {

		// write your logic here

		return null;
	}

	@Override
	public Integer addTransaction(TransactionDTO transactionDTO) throws EKartException {
		if (transactionDTO.getTransactionStatus().equals(TransactionStatus.TRANSACTION_FAILED)) {
			throw new EKartException("PaymentService.TRANSACTION_FAILED_CVV_NOT_MATCHING");
		}
		Transaction transaction = new Transaction();
		transaction.setCardId(transactionDTO.getCard().getCardId());

		transaction.setOrderId(transactionDTO.getOrder().getOrderId());
		transaction.setTotalPrice(transactionDTO.getTotalPrice());
		transaction.setTransactionDate(transactionDTO.getTransactionDate());
		transaction.setTransactionStatus(transactionDTO.getTransactionStatus());
		transactionRepository.save(transaction);

		return transaction.getTransactionId();
	}

	@Override
	public TransactionDTO authenticatePayment(String customerEmailId, TransactionDTO transactionDTO)
			throws EKartException, NoSuchAlgorithmException {
		if (!transactionDTO.getOrder().getCustomerEmailId().equals(customerEmailId)) {
			throw new EKartException("PaymentService.ORDER_DOES_NOT_BELONGS");

		}

		if (!transactionDTO.getOrder().getOrderStatus().equals("PLACED")) {
			throw new EKartException("PaymentService.TRANSACTION_ALREADY_DONE");

		}
		CardDTO cardDTO = getCard(transactionDTO.getCard().getCardId());
		if (!cardDTO.getCustomerEmailId().matches(customerEmailId)) {

			throw new EKartException("PaymentService.CARD_DOES_NOT_BELONGS");
		}
		if (!cardDTO.getCardType().equals(transactionDTO.getOrder().getPaymentThrough())) {

			throw new EKartException("PaymentService.PAYMENT_OPTION_SELECTED_NOT_MATCHING_CARD_TYPE");
		}
		if (cardDTO.getHashCvv().equals(HashingUtility.getHashValue(transactionDTO.getCard().getCvv().toString()))) {

			transactionDTO.setTransactionStatus(TransactionStatus.TRANSACTION_SUCCESS);
		} else {

			transactionDTO.setTransactionStatus(TransactionStatus.TRANSACTION_FAILED);

		}

		return transactionDTO;
	}
}
