package com.infy.ekart.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;


import com.infy.ekart.dto.CustomerDTO;
import com.infy.ekart.entity.Customer;
import com.infy.ekart.exception.EKartException;
import com.infy.ekart.repository.CustomerRepository;

//Add the missing annotation
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	// This method will authenticate customer email id and password and return
	// customer details
	@Override
	public CustomerDTO authenticateCustomer(String emailId, String password) throws EKartException {
		CustomerDTO customerDTO = null;

		// retrieving customer data from repository
		Optional<Customer> optionalCustomer = customerRepository.findById(emailId.toLowerCase());
		Customer customer = optionalCustomer
				.orElseThrow(() -> new EKartException("CustomerService.CUSTOMER_NOT_FOUND"));
		// comparing entered password with password stored in DB
		if (!password.equals(customer.getPassword()))
			throw new EKartException("CustomerService.INVALID_CREDENTIALS");

		customerDTO = new CustomerDTO();
		customerDTO.setEmailId(customer.getEmailId());
		customerDTO.setName(customer.getName());
		customerDTO.setPhoneNumber(customer.getPhoneNumber());
		customerDTO.setAddress(customer.getAddress());
		return customerDTO;

	}

	// This method will add a new customer
	@Override
	public String registerNewCustomer(CustomerDTO customerDTO) throws EKartException {
		String registeredWithEmailId = null;
		// check whether specified email id is already in use by other customer
		boolean isEmailNotAvailable = customerRepository.findById(customerDTO.getEmailId().toLowerCase()).isEmpty();
		// check whether specified phone no. is already in use by other customer
		boolean isPhoneNumberNotAvailable = customerRepository.findByPhoneNumber(customerDTO.getPhoneNumber())
				.isEmpty();
		if (isEmailNotAvailable) {
			if (isPhoneNumberNotAvailable) {
				Customer customer = new Customer();
				customer.setEmailId(customerDTO.getEmailId().toLowerCase());
				customer.setName(customerDTO.getName());
				customer.setPassword(customerDTO.getPassword());
				customer.setPhoneNumber(customerDTO.getPhoneNumber());
				customer.setAddress(customerDTO.getAddress());
				customerRepository.save(customer);
				registeredWithEmailId = customer.getEmailId();
			} else {
				throw new EKartException("CustomerService.PHONE_NUMBER_ALREADY_IN_USE");
			}
		} else {
			throw new EKartException("CustomerService.EMAIL_ID_ALREADY_IN_USE");
		}
		return registeredWithEmailId;

	}

	// this method will update the address
	@Override
	public void updateShippingAddress(String customerEmailId, String address) throws EKartException {
		// retrieving address details from repository
		Optional<Customer> optionalCustomer = customerRepository.findById(customerEmailId.toLowerCase());
		Customer customer = optionalCustomer
				.orElseThrow(() -> new EKartException("CustomerService.CUSTOMER_NOT_FOUND"));
		customer.setAddress(address);
	}

	// this method will remove address from a particular customer details
	@Override
	public void deleteShippingAddress(String customerEmailId) throws EKartException {
		// retrieving customer details from repository
		Optional<Customer> optionalCustomer = customerRepository.findById(customerEmailId.toLowerCase());
		Customer customer = optionalCustomer
				.orElseThrow(() -> new EKartException("CustomerService.CUSTOMER_NOT_FOUND"));
		customer.setAddress(null);
	}

	// Get the customer detail by using the emailId from repository
	// If not found, throw EKartException with message
	// CustomerService.CUSTOMER_NOT_FOUND
	// else return the customer details
	@Override
	public CustomerDTO getCustomerByEmailId(String emailId) throws EKartException {
		// write your logic here

		return null;

	}

}
