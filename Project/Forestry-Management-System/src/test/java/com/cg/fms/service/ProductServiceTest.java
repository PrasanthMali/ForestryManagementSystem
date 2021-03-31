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

import com.cg.fms.dao.ProductDao;
import com.cg.fms.entity.Product;
import com.cg.fms.exception.CustomerException;
import com.cg.fms.exception.ProductException;
import com.cg.fms.model.ProductModel;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
	@Mock
	private ProductDao productDao;
	
	@InjectMocks
	private ProductServiceImpl service;
	
	
	@Test
	@DisplayName("Product Details add")
	void testAdd() throws CustomerException, ProductException {
		Product product=new Product("1","timber wood","best timber wood","100",null,null);
		
		Mockito.when(productDao.save(product)).thenReturn(product);

		ProductModel expected=new ProductModel("1","timber wood","best timber wood","100",null,null);
		
		ProductModel actual = service.addProduct(expected);
		
		assertEquals(expected,actual);

	}
	
	@Test
	@DisplayName("Product Details should delete")
	void testDelete() throws CustomerException, ProductException {
		Product customer=new Product("1","timber wood","best timber wood","100",null,null);
		
		Mockito.when(productDao.save(customer)).thenReturn(customer);

		ProductModel expected=new ProductModel("1","timber wood","best timber wood","100",null,null);
		
		ProductModel added = service.addProduct(expected);
		
		assertEquals(expected,added);
		
		Mockito.doNothing().when(productDao).deleteById(added.getProductId());

		service.deleteProduct(added.getProductId());
	}
	
	@Test
	@DisplayName("get by Id ")
	void testGetById () throws ProductException {
		Product testdata=new Product("1","timber wood","best timber wood","100",null,null);
		
		Product expected=new Product("1","timber wood","best timber wood","100",null,null);
		
		
		Mockito.when(productDao.findById(testdata.getProductId())).thenReturn(Optional.of(testdata));
	
		ProductModel actual=service.getProductByProductId(testdata.getProductId());
		
		assertEquals(expected,actual);
	}
	
	@Test
	@DisplayName("get by id return null")
	void testGetByIdNull() throws ProductException {		
		
		Mockito.when(productDao.findById("1")).thenReturn(Optional.empty());
		
		ProductModel actual = service.getProductByProductId("1");
		assertNull(actual);
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
