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

import com.cg.fms.dao.LandDao;
import com.cg.fms.entity.Land;
import com.cg.fms.exception.ContractException;
import com.cg.fms.exception.LandException;
import com.cg.fms.model.LandModel;

@ExtendWith(MockitoExtension.class)
public class LandServiceTest {
	@Mock
	private LandDao landDao;
	
	@InjectMocks
	private LandServiceImpl service;
	
	@Test
	@DisplayName("Land Details add")
	void testAdd() throws ContractException, LandException {
		Land testdata=new Land("1","1","abhishek","100000");
		
		Mockito.when(landDao.save(testdata)).thenReturn(testdata);

		LandModel expected=new LandModel("1","1","abhishek","100000");
		
		LandModel actual =service.addLand(expected);
		
		assertEquals(expected,actual);

	}
	
	@Test
	@DisplayName("Land Details should delete")
	void testDelete() throws ContractException, LandException {
		Land testdata=new Land("1","1","abhishek","100000");
		
		Mockito.when(landDao.save(testdata)).thenReturn(testdata);

		LandModel expected=new LandModel("1","1","abhishek","100000");
		
		LandModel added = service.addLand(expected);
		
		assertEquals(expected,added);
		
		Mockito.doNothing().when(landDao).deleteById(added.getLandId());

//		service.deleteLand(added.getLandId());
	}
	
	
	@Test
	@DisplayName("get by Id ")
	void testGetById () throws LandException {
		Land testdata=new Land("1","1","abhishek","100000");
		
		Land expected=new Land("1","1","abhishek","100000");
		
		
		Mockito.when(landDao.findById(testdata.getLandId())).thenReturn(Optional.of(testdata));
	
		LandModel actual= service.getLand(expected.getLandId());
		
		assertEquals(expected,actual);
	}
	
	@Test
	@DisplayName("get by id return null")
	void testGetByIdNull() throws LandException {		
		
		Mockito.when(landDao.findById("1")).thenReturn(Optional.empty());
		
		LandModel actual = service.getLand("1");
		assertNull(actual);
	}
	

}
