package com.cg.fms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.fms.dao.AdminDao;
import com.cg.fms.entity.Admin;
import com.cg.fms.exception.AdminException;
import com.cg.fms.model.AdminModel;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {
	@Mock
	private AdminDao adminDao;
	
	@InjectMocks
	private AdminServiceImpl service;
	@Test
	@DisplayName("Admin Details should retrive")
	void testGetAll() {
		List<Admin> testData=Arrays.asList(new Admin[] {
				new Admin("1","abhishek","123456789"),
				new Admin("2","paresh","123456789"),
				new Admin("3","avinash","12397897")
		});
		
		Mockito.when(adminDao.findAll()).thenReturn(testData);
		
		List<AdminModel> expected=Arrays.asList(new AdminModel[] {
				new AdminModel("1","abhishek","123456789"),
				new AdminModel("2","paresh","123456789"),
				new AdminModel("3","avinash","12397897")
		});
		
		List<AdminModel> actual = service.getAllAdmins();
		
		assertEquals(expected,actual);

	}
	
	@Test
	@DisplayName("Admin Details add")
	void testAdd() throws AdminException {
		Admin admin1=new Admin("1","abhishek","123456789");
		
		Mockito.when(adminDao.save(admin1)).thenReturn(admin1);

		AdminModel expected=new AdminModel("1","abhishek","123456789");
		
		AdminModel actual = service.addAdmin(expected);
		
		assertEquals(expected,actual);

	}

	
	@Test
	@DisplayName("get by Id ")
	void testGetById () throws AdminException {
		Admin testdata=new Admin("1","abhishek","123456789");
		
		AdminModel expected=new AdminModel("1","abhishek","123456789");
		
		Mockito.when(adminDao.findById(testdata.getAdminId())).thenReturn(Optional.of(testdata));
	
		
		AdminModel actual=service.getAdmin(expected.getAdminId());
		System.out.println(actual+" "+expected);
		assertEquals(expected,actual);
	}
	
	@Test
	@DisplayName("get by id return null")
	void testGetByIdNull() throws AdminException {		
		
		Mockito.when(adminDao.findById("1")).thenReturn(Optional.empty());
		
		AdminModel actual = service.getAdmin("1");
		assertNull(actual);
	}
	
}