package com.cg.fms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fms.dao.CustomerDao;
import com.cg.fms.entity.Customer;
import com.cg.fms.exception.CustomerException;
import com.cg.fms.model.CustomerModel;

@Service
public class CustomerServiceImpl implements ICustomerService {
	@Autowired
	private CustomerDao customerRepo;
	
	@Autowired
	private EMParser parser;
	
	public CustomerServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	

	public CustomerServiceImpl(CustomerDao customerRepo) {
		super();
		this.customerRepo = customerRepo;
		this.parser = new EMParser();
	}
	
	


	public CustomerDao getCustomerRepo() {
		return customerRepo;
	}



	public void setCustomerRepo(CustomerDao customerRepo) {
		this.customerRepo = customerRepo;
	}



	public EMParser getParser() {
		return parser;
	}



	public void setParser(EMParser parser) {
		this.parser =new EMParser();
	}

	@Override
	public CustomerModel getCustomer(String customerId) throws CustomerException {
		if (!customerRepo.existsById(customerId))
			throw new CustomerException("No Customer found for the given Id");
		return parser.parse(customerRepo.findById(customerId).orElse(null));
	}

	@Override
	public CustomerModel addCustomer(CustomerModel customer) throws CustomerException {
		if (customer != null) {
			if (customerRepo.existsById(customer.getCustomerId())) {
				throw new CustomerException("Customer with this id already exists");
			}

			customer = parser.parse(customerRepo.save(parser.parse(customer)));
		}

		return customer;
	}



	@Override
	public void deleteCustomer(String customerId) {
		customerRepo.deleteById(customerId);
	}

	@Override
	public List<CustomerModel> getAllCustomers() {
		return customerRepo.findAll().stream().map(parser::parse).collect(Collectors.toList());
	}
	

//	@Transactional
//	@Override
//	public AdminModel updateAdmin(String adminId, AdminModel adminModel) throws AdminException {
//		if(adminModel != null) {
//			if (!adminDao.existsById(adminId)) {
//				throw new AdminException("Admin Not present in DB.");
//			}
//			adminModel = parser.parse((adminDao.save(parser.parse(adminModel))));
//		}
//		return adminModel;
//	}

	@Override
	public Customer updateCustomer(String customerId, Customer customer) throws CustomerException {
		if (customer != null) {
			if (customerRepo.existsById(customer.getCustomerId())) {
				customer = customerRepo.save(customer);
			}
			else {
				throw new CustomerException("Customer not present in DB.");
			}
			
		}
		return customer;
	}

}
