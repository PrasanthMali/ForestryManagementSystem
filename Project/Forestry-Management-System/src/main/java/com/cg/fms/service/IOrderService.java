package com.cg.fms.service;

import java.util.List;
import com.cg.fms.exception.OrderException;
import com.cg.fms.model.OrderModel;

public interface IOrderService {

	OrderModel updateOrder(OrderModel orderModel);

	void deleteOrder(String orderNumber);

	List<OrderModel> getAllOrders();


//	List<OrderModel> getOrderByCustomerId(String customerId) throws OrderException;

	OrderModel getOrder(String orderNumber) throws OrderException;

	OrderModel addOrder(OrderModel expected) throws OrderException;

}
