package com.cg.fms.service;

import java.util.List;

import com.cg.fms.entity.Customer;
import com.cg.fms.exception.CustomerException;
import com.cg.fms.model.CustomerModel;

public interface ICustomerService {

	Customer updateCustomer(String customerId, Customer customer) throws CustomerException;

	List<CustomerModel> getAllCustomers();

	void deleteCustomer(String customerId);

	CustomerModel getCustomer(String customerId) throws CustomerException;

	CustomerModel addCustomer(CustomerModel customer) throws CustomerException;

	boolean existsById(String customerId) throws CustomerException;

	CustomerModel findById(String customerId) throws CustomerException;

}
