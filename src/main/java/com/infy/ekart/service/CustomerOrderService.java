package com.infy.ekart.service;

import java.util.List;

import com.infy.ekart.dto.OrderDTO;
import com.infy.ekart.dto.OrderStatus;
import com.infy.ekart.dto.PaymentThrough;
import com.infy.ekart.exception.EKartException;



public interface CustomerOrderService {
	 Integer placeOrder(OrderDTO orderDTO) throws EKartException;
	 OrderDTO getOrderDetails (Integer orderId) throws EKartException;
	 List<OrderDTO> findOrdersByCustomerEmailId(String emailId) throws EKartException;
	 void updateOrderStatus( Integer orderId , OrderStatus orderStatus) throws EKartException;
	 void updatePaymentThrough( Integer orderId , PaymentThrough paymentThrough) throws EKartException;

}
