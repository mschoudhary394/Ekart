package com.infy.ekart.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.infy.ekart.dto.CustomerDTO;
import com.infy.ekart.dto.OrderDTO;
import com.infy.ekart.dto.OrderStatus;
import com.infy.ekart.dto.OrderedProductDTO;
import com.infy.ekart.dto.PaymentThrough;
import com.infy.ekart.entity.Order;
import com.infy.ekart.entity.OrderedProduct;
import com.infy.ekart.exception.EKartException;
import com.infy.ekart.repository.CustomerOrderRepository;

//Add the missing annotation
public class CustomerOrderServiceImpl implements CustomerOrderService {

	private CustomerOrderRepository orderRepository;

	private CustomerService customerService;

	@Override
	public Integer placeOrder(OrderDTO orderDTO) throws EKartException {
		CustomerDTO customerDTO = customerService.getCustomerByEmailId(orderDTO.getCustomerEmailId());
		if (customerDTO.getAddress().isBlank() || customerDTO.getAddress() == null) {
			throw new EKartException("OrderService.ADDRESS_NOT_AVAILABLE");
		}

		Order order = new Order();
		order.setDeliveryAddress(customerDTO.getAddress());
		order.setCustomerEmailId(orderDTO.getCustomerEmailId());
		order.setDateOfDelivery(orderDTO.getDateOfDelivery());
		order.setDateOfOrder(LocalDateTime.now());
		order.setPaymentThrough(PaymentThrough.valueOf(orderDTO.getPaymentThrough()));
		if (order.getPaymentThrough().equals(PaymentThrough.CREDIT_CARD)) {
			order.setDiscount(10.00d);
		} else {
			order.setDiscount(5.00d);

		}

		order.setOrderStatus(OrderStatus.PLACED);
		Double price = 0.0;
		List<OrderedProduct> orderedProducts = new ArrayList<OrderedProduct>();

		for (OrderedProductDTO orderedProductDTO : orderDTO.getOrderedProducts()) {
			if (orderedProductDTO.getProduct().getAvailableQuantity() < orderedProductDTO.getQuantity()) {
				throw new EKartException("OrderService.INSUFFICIENT_STOCK");
			}

			OrderedProduct orderedProduct = new OrderedProduct();
			orderedProduct.setProductId(orderedProductDTO.getProduct().getProductId());
			orderedProduct.setQuantity(orderedProductDTO.getQuantity());
			orderedProducts.add(orderedProduct);
			price = price + orderedProductDTO.getQuantity() * orderedProductDTO.getProduct().getPrice();

		}

		order.setOrderedProducts(orderedProducts);

		order.setTotalPrice(price * (100 - order.getDiscount()) / 100);

		orderRepository.save(order);

		return order.getOrderId();
	}

	// Get the Order details by using the OrderId
	// If not found throw EKartException with message OrderService.ORDER_NOT_FOUND
	// Else return the order details along with the ordered products
	@Override
	public OrderDTO getOrderDetails(Integer orderId) throws EKartException {

		// write your logic here
		return null;
	}

	// Get the Order details by using the OrderId
	// If not found throw EKartException with message OrderService.ORDER_NOT_FOUND
	// Else update the order status with the given order status
	@Override
	public void updateOrderStatus(Integer orderId, OrderStatus orderStatus) throws EKartException {
		// write your logic here
	}

	// Get the Order details by using the OrderId
	// If not found throw EKartException with message OrderService.ORDER_NOT_FOUND
	// Else check if the order status is already confirmed, if yes then throw
	// EKartException with message OrderService.TRANSACTION_ALREADY_DONE
	// Else update the paymentThrough with the given paymentThrough option
	@Override
	public void updatePaymentThrough(Integer orderId, PaymentThrough paymentThrough) throws EKartException {

		// write your logic here
	}

	// Get the list of Order details by using the emailId
	// If the list is empty throw EKartException with message
	// OrderService.NO_ORDERS_FOUND
	// Else populate the order details along with ordered products and return that
	// list

	@Override
	public List<OrderDTO> findOrdersByCustomerEmailId(String emailId) throws EKartException {
		// write your logic here
		return null;
	}

}
