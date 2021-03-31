package com.cg.fms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.fms.dao.OrderDao;
import com.cg.fms.entity.Order;
import com.cg.fms.exception.OrderException;
import com.cg.fms.model.OrderModel;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
	@Mock
	private OrderDao orderDao;
	
	@InjectMocks
	private OrderServiceImpl service;
	
	@Test
	@DisplayName("Order Details add")
	void testAdd() throws OrderException {
		Order order=new Order("1","Chennai","2020-11-11","100",null,null);
		
		Mockito.when(orderDao.save(order)).thenReturn(order);

		OrderModel expected=new OrderModel("1","Chennai","2020-11-11","100",null,null);
		
		OrderModel actual = service.addOrder(expected);
		
		assertEquals(expected,actual);

	}
	
	@Test
	@DisplayName("Order Details should delete")
	void testDelete() throws OrderException {
		Order order=new Order("1","Chennai","2020-11-11","100",null,null);
		
		Mockito.when(orderDao.save(order)).thenReturn(order);

		OrderModel expected=new OrderModel("1","Chennai","2020-11-11","100",null,null);
		
		OrderModel added = service.addOrder(expected);
		
		assertEquals(expected,added);
		
		Mockito.doNothing().when(orderDao).deleteById(added.getOrderNumber());

		service.deleteOrder(added.getOrderNumber());
	}
	
	@Test
	@DisplayName("get by Id ")
	void testGetById () throws OrderException {
		Order testdata=new Order("1","Chennai","2020-11-11","100",null,null);
		
		Order expected=new Order("1","Chennai","2020-11-11","100",null,null);
		
		
		Mockito.when(orderDao.findById(testdata.getOrderNumber())).thenReturn(Optional.of(testdata));
	
		OrderModel actual=service.getOrder(testdata.getOrderNumber());
		
		assertEquals(expected,actual);
	}
	
	@Test
	@DisplayName("get by id return null")
	void testGetByIdNull() throws OrderException {		
		
		Mockito.when(orderDao.findById("1")).thenReturn(Optional.empty());
		
		OrderModel actual = service.getOrder("1");
		assertNull(actual);
	}
	
	
	
	
	

}
