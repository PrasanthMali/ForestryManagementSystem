package com.cg.fms.service;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fms.dao.OrderDao;
import com.cg.fms.exception.OrderException;
import com.cg.fms.model.OrderModel;

@Service
public class OrderServiceImpl implements IOrderService{
	
	@Autowired
	private OrderDao orderRepo;
	
	@Autowired
	private EMParser parser;
	
	
	public OrderServiceImpl(OrderDao orderRepo) {
		super();
		this.orderRepo = orderRepo;
		this.parser =new EMParser();
	}
	
	

	public OrderDao getOrderRepo() {
		return orderRepo;
	}



	public void setOrderRepo(OrderDao orderRepo) {
		this.orderRepo = orderRepo;
	}



	public EMParser getParser() {
		return parser;
	}



	public void setParser(EMParser parser) {
		this.parser = parser;
	}

	@Override
	public OrderModel getOrder(String orderNumber) throws OrderException {
		if (!orderRepo.existsById(orderNumber))
			throw new OrderException("No order found for the given Id");
		return parser.parse(orderRepo.findById(orderNumber).get());
	}
//	@Override
//	public List<OrderModel> getOrderByCustomerId(String customerId) throws OrderException {
////		return contractRepo.findByCustomerId(customerId);
//		List<Order> order = orderRepo.findAll();
//		List<OrderModel> orderCustomer=new ArrayList<>();
//		Iterator<Order> cust = order.stream().iterator();
//		while(cust.hasNext()) {
//			Order val= orderRepo.findById(cust.next().getOrderNumber()).orElse(null);
//			
//			System.out.println(val.getCustomer().getCustomerId());
//			if(val.getCustomer().getCustomerId().equals(customerId)) {
//				System.out.println(val);
//				orderCustomer.add(parser.parse(val));
//				System.out.println(orderCustomer);
//			}
//		}
//		System.out.println(orderCustomer+" "+orderCustomer.size());
//		return orderCustomer;
//	}

	@Override
	public OrderModel addOrder(OrderModel expected) throws OrderException{
		if ( expected!= null) {
			if (orderRepo.existsById(expected.getOrderNumber())) {
				throw new OrderException("Order with this id already exists");
			}

			expected = parser.parse(orderRepo.save(parser.parse(expected)));
		}

		return expected;
	}
	@Override
	public OrderModel updateOrder(OrderModel orderModel) {
		if (orderModel != null) {
			if (orderRepo.existsById(orderModel.getOrderNumber())) {
				orderModel = parser.parse(orderRepo.save(parser.parse(orderModel)));
			}
			
		}
		return orderModel;
	}

	@Override
	public void deleteOrder(String orderNumber) {
		orderRepo.deleteById(orderNumber);
	}
	@Override
	public List<OrderModel> getAllOrders() {
		return orderRepo.findAll().stream().map(parser::parse).collect(Collectors.toList());
	}
	
}
