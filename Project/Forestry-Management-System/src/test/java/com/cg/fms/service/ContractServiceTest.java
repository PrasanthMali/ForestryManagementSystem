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

import com.cg.fms.dao.ContractDao;

import com.cg.fms.entity.Contract;

import com.cg.fms.exception.ContractException;

import com.cg.fms.model.ContractModel;

@ExtendWith(MockitoExtension.class)
public class ContractServiceTest {
	@Mock
	private ContractDao contractDao;
	
	@InjectMocks
	private ContractServiceImpl service;
	
	@Test
	@DisplayName("Contract Details add")
	void testAdd() throws ContractException {
		Contract admin=new Contract("1","chennai","2020-11-23","100",null,null);
		
		Mockito.when(contractDao.save(admin)).thenReturn(admin);

		ContractModel expected=new ContractModel("1","chennai","2020-11-23","100",null,null);
		
		ContractModel actual = service.addContract(expected);
		
		assertEquals(expected,actual);

	}
	
	@Test
	@DisplayName("contract Details should delete")
	void testDelete() throws ContractException {
		Contract contract=new Contract("1","chennai","2020-11-23","100",null,null);
		
		Mockito.when(contractDao.save(contract)).thenReturn(contract);

		ContractModel expected=new ContractModel("1","chennai","2020-11-23","100",null, null);
		
		ContractModel added = service.addContract(expected);
		
		assertEquals(expected,added);
		
		Mockito.doNothing().when(contractDao).deleteById(added.getContractNumber());

		service.deleteContract(added.getContractNumber());
	}
	
	
	@Test
	@DisplayName("get by Id ")
	void testGetById () throws ContractException {
		Contract testdata=new Contract("1","chennai","2020-11-23","100",null,null);
		
		Contract expected=new Contract("1","chennai","2020-11-23","100",null,null);
		
		
		Mockito.when(contractDao.findById(testdata.getContractNumber())).thenReturn(Optional.of(testdata));
	
		ContractModel actual=service.getContarctByContractNumber(testdata.getContractNumber());
		
		assertEquals(expected,actual);
	}
	
	@Test
	@DisplayName("get by id return null")
	void testGetByIdNull() throws ContractException {		
		
		Mockito.when(contractDao.findById("19")).thenReturn(Optional.empty());
		
		ContractModel actual = service.getContarctByContractNumber("19");
		assertNull(actual);
	}
	
	
}
