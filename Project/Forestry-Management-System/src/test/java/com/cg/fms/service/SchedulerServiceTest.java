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

import com.cg.fms.dao.SchedulerDao;

import com.cg.fms.entity.Scheduler;
import com.cg.fms.exception.CustomerException;
import com.cg.fms.exception.SchedulerException;

import com.cg.fms.model.SchedulerModel;

@ExtendWith(MockitoExtension.class)
public class SchedulerServiceTest {
	@Mock
	private SchedulerDao schedulerDao;
	
	@InjectMocks
	private SchedulerServiceImpl service;
	
	@Test
	@DisplayName("Scheduler Details add")
	void testAdd() throws SchedulerException {
		Scheduler testdata=new Scheduler("1","abhishek","9632587741","TN-A2-021");
		
		Mockito.when(schedulerDao.save(testdata)).thenReturn(testdata);

		SchedulerModel expected=new SchedulerModel("1","abhishek","9632587741","TN-A2-021");
		
		SchedulerModel actual = service.addScheduler(expected);
		
		assertEquals(expected,actual);

	}
	
	@Test
	@DisplayName("Scheduler Details should delete")
	void testDelete() throws CustomerException, SchedulerException {
		Scheduler scheduler=new Scheduler("1","abhishek","9632587741","TN-A2-021");
		
		Mockito.when(schedulerDao.save(scheduler)).thenReturn(scheduler);

		SchedulerModel expected=new SchedulerModel("1","abhishek","9632587741","TN-A2-021");
		
		SchedulerModel added = service.addScheduler(expected);
		
		assertEquals(expected,added);
		
		Mockito.doNothing().when(schedulerDao).deleteById(added.getSchedulerId());

		service.deleteScheduler(added.getSchedulerId());
	}
	
	@Test
	@DisplayName("get by Id ")
	void testGetById () throws SchedulerException {
		Scheduler testdata=new Scheduler("1","abhishek","9632587741","TN-A2-021");
		
		Scheduler expected=new Scheduler("1","abhishek","9632587741","TN-A2-021");
		
		
		Mockito.when(schedulerDao.findById(testdata.getSchedulerId())).thenReturn(Optional.of(testdata));
	
		SchedulerModel actual=service.getScheduler(testdata.getSchedulerId());
		
		assertEquals(expected,actual);
	}
	
	@Test
	@DisplayName("get by id return null")
	void testGetByIdNull() throws SchedulerException {		
		
		Mockito.when(schedulerDao.findById("1")).thenReturn(Optional.empty());
		
		SchedulerModel actual = service.getScheduler("1");
		assertNull(actual);
	}

}
