package com.cg.fms.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.fms.entity.Customer;
import com.cg.fms.exception.ContractException;
import com.cg.fms.exception.CustomerException;
import com.cg.fms.model.CustomerModel;
import com.cg.fms.service.ICustomerService;

@RestController
@RequestMapping(path="/customers")
public class CustomerAPI {
	
	@Autowired
	private ICustomerService customerService;
	@GetMapping("/getcustomer/{customerId}")
	public ResponseEntity<CustomerModel> getCustomer(@PathVariable("customerId") String customerId) throws  CustomerException{
		return ResponseEntity.ok(customerService.getCustomer(customerId));
	}
	
	@GetMapping("/getallcustomers")
	public ResponseEntity<List<CustomerModel>> getAll() throws ContractException{
		return ResponseEntity.ok(customerService.getAllCustomers());
	}
	
	@PostMapping("/addcustomer")
	public ResponseEntity<CustomerModel> createAdmin(@RequestBody CustomerModel customer) throws CustomerException {
		customer = customerService.addCustomer(customer);
		return new ResponseEntity<>(customer, HttpStatus.CREATED);
	}
	
	@PutMapping("/updatecustomer/{customerId}")
	public ResponseEntity<Customer> updateContract(@RequestBody Customer customer,@PathVariable("customerId")String customerId) throws ContractException, CustomerException{
		customer = customerService.updateCustomer(customerId, customer);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}
	
	@DeleteMapping("/deletecustomer/{customerId}")
	public ResponseEntity<String> deleteContract(@PathVariable("customerId") String customerId) throws CustomerException {
		ResponseEntity<String> response = null;
		CustomerModel customer = customerService.getCustomer(customerId);
		if (customer == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			customerService.deleteCustomer(customerId);
			response = new ResponseEntity<>("Customer is deleted successsfully", HttpStatus.OK);
		}
		return response;
	}

}
