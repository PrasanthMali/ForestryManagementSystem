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

import com.cg.fms.dao.CustomerDao;

import com.cg.fms.entity.Customer;
import com.cg.fms.exception.CustomerException;
import com.cg.fms.model.CustomerModel;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
	@Mock
	private CustomerDao customerDao;
	
	@InjectMocks
	private CustomerServiceImpl service;
	
	@Test
	@DisplayName("Customer Details add")
	void testAdd() throws CustomerException {
		Customer testdata=new Customer("1","abhishek","123456789","abhishekkvs1000@gmail.com","street A","Chennai","600062","9632587411");
		
		Mockito.when(customerDao.save(testdata)).thenReturn(testdata);

		CustomerModel expected=new CustomerModel("1","abhishek","123456789","abhishekkvs1000@gmail.com","street A","Chennai","600062","9632587411");
		
		CustomerModel actual = service.addCustomer(expected);
		
		assertEquals(expected,actual);

	}
	
	@Test
	@DisplayName("Customer Details should delete")
	void testDelete() throws CustomerException {
		Customer testdata=new Customer("1","abhishek","123456789","abhishekkvs1000@gmail.com","street A","Chennai","600062","9632587411");
		
		Mockito.when(customerDao.save(testdata)).thenReturn(testdata);

		CustomerModel expected=new CustomerModel("1","abhishek","123456789","abhishekkvs1000@gmail.com","street A","Chennai","600062","9632587411");
		
		CustomerModel added = service.addCustomer(expected);
		
		assertEquals(expected,added);
		
		Mockito.doNothing().when(customerDao).deleteById(added.getCustomerId());

		service.deleteCustomer(added.getCustomerId());
	}
	
	@Test
	@DisplayName("get by Id ")
	void testGetById () throws CustomerException {
		Customer testdata=new Customer("1","abhishek","123456789","abhishekkvs1000@gmail.com","street 101","Chennai","600062","9632587411");
		
		Customer expected=new Customer("1","abhishek","123456789","abhishekkvs1000@gmail.com","street 101","Chennai","600062","9632587411");
		
		
		Mockito.when(customerDao.findById(testdata.getCustomerId())).thenReturn(Optional.of(testdata));
	
		//CustomerModel actual=(CustomerModel) service.getCustomer(testdata.getCustomerId());
		
		CustomerModel actual = service.getCustomer(expected.getCustomerId());
		System.out.println(actual+" "+expected);
		assertEquals(expected,actual);
	}
	
	@Test
	@DisplayName("get by id return null")
	void testGetByIdNull() throws CustomerException {		
		
		Mockito.when(customerDao.findById("1")).thenReturn(Optional.empty());
		
		CustomerModel actual = service.getCustomer("1");
		assertNull(actual);
	}
	
	

}
