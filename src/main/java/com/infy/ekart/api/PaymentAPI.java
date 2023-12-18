package com.infy.ekart.api;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.infy.ekart.dto.CardDTO;
import com.infy.ekart.exception.EKartException;
import com.infy.ekart.service.PaymentService;

//Add the missing annotations
@RequestMapping(value = "/payment-api")

public class PaymentAPI {

	private PaymentService paymentService;

	private Environment environment;

	private RestTemplate template;

	Log logger = LogFactory.getLog(PaymentAPI.class);

	// This api will add a new card for particular customer by calling
	// addCustomerCard() of PaymentService, which in turn returns cardId
	// Set the success message with value PaymentAPI.CUSTOMER_CARD_DELETED_SUCCESS
	// appended with cardId
	// and return the same
	//
	@PostMapping(value = "/customer/{customerEmailId:.+}/cards")
	public ResponseEntity<String> addNewCard(@RequestBody CardDTO cardDTO,
			@Pattern(regexp = "[a-zA-Z0-9._]+@[a-zA-Z]{2,}\\.[a-zA-Z][a-zA-Z.]+", message = "{invalid.email.format}") @PathVariable("customerEmailId") String customerEmailId)
			throws EKartException, NoSuchAlgorithmException {
		// write your logic here

		return null;
	}

	@PutMapping(value = "/update/card")
	public ResponseEntity<String> updateCustomerCard(@Valid @RequestBody CardDTO cardDTO)
			throws EKartException, NoSuchAlgorithmException {
		logger.info("Recieved request to update  card :" + cardDTO.getCardId() + " of customer : "
				+ cardDTO.getCustomerEmailId());

		paymentService.updateCustomerCard(cardDTO);
		String modificationSuccessMsg = environment.getProperty("PaymentAPI.UPDATE_CARD_SUCCESS");
		return new ResponseEntity<>(modificationSuccessMsg, HttpStatus.OK);

	}

	// Delete the customer cards details by calling deleteCustomerCard()
	// method of PaymentService()
	// Set the success message with value PaymentAPI.CUSTOMER_CARD_DELETED_SUCCESS
	// and return the same
	@DeleteMapping(value = "/customer/{customerEmailId:.+}/card/{cardID}/delete")
	public ResponseEntity<String> deleteCustomerCard(@PathVariable("cardID") Integer cardID,
			@Pattern(regexp = "[a-zA-Z0-9._]+@[a-zA-Z]{2,}\\.[a-zA-Z][a-zA-Z.]+", message = "{invalid.email.format}") @PathVariable("customerEmailId") String customerEmailId)
			throws EKartException {

		// write your logic here

		return null;
	}

	// Get the customer cards details by calling getCardsOfCustomer()
	// method of PaymentService() and return the list of card details obtained
	@GetMapping(value = "/customer/{customerEmailId}/card-type/{cardType}")
	public ResponseEntity<List<CardDTO>> getCardsOfCustomer(@PathVariable String customerEmailId,
			@PathVariable String cardType) throws EKartException {
		// write your logic here

		return null;
	}

	// Get the order details of Customer for the given orderId by calling respective
	// API
	// Update the Transaction details with the obtained Order details in above step,
	// along with transaction date and total price
	// Authenticate the transaction details for the given customer by calling
	// authenticatePayment() method of PaymentService
	// Add the transaction details to the database by calling addTransaction()
	// method of PaymentService
	// Update the order status by calling by calling respective API
	// Set the appropriate success message and return the same
	@PostMapping(value = "/customer/{customerEmailId}/order/{orderId}")
	public ResponseEntity<String> payForOrder(
			@Pattern(regexp = "[a-zA-Z0-9._]+@[a-zA-Z]{2,}\\.[a-zA-Z][a-zA-Z.]+", message = "{invalid.email.format}") @PathVariable("customerEmailId") String customerEmailId,
			@NotNull(message = "{orderId.absent") @PathVariable("orderId") Integer orderId,
			@Valid @RequestBody CardDTO cardDTO) throws NoSuchAlgorithmException, EKartException {

		// write your logic here

		return null;
	}

}
