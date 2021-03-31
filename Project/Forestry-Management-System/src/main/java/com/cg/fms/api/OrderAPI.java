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
import com.cg.fms.exception.OrderException;
import com.cg.fms.model.OrderModel;
import com.cg.fms.service.IOrderService;

@RestController
@RequestMapping(path="/orders")
public class OrderAPI {
	@Autowired
	private IOrderService orderService;
	
	
	@PostMapping("/addorder")
	public ResponseEntity<OrderModel> createOrder(@RequestBody OrderModel order) throws OrderException {
		order = orderService.addOrder(order);
		return new ResponseEntity<>(order, HttpStatus.CREATED);
	}
	
	//update
	@PutMapping("/updateorder/{orderNumber}")
	public ResponseEntity<OrderModel> updateOrder(@RequestBody OrderModel orderModel) throws OrderException {
		orderModel = orderService.updateOrder(orderModel);
		return new ResponseEntity<>(orderModel, HttpStatus.OK);
	}
	
	
	//display all order
	@GetMapping("/getallorders")
	public ResponseEntity<List<OrderModel>> getAll() throws OrderException{
		return ResponseEntity.ok(orderService.getAllOrders());
	}
	
//	@GetMapping("/getOrderByCustomerId/{customerId}")
//	public ResponseEntity<List<OrderModel>> getOrderByCustomerId(@PathVariable("customerId") String customerId) throws OrderException{
//		return ResponseEntity.ok(orderService.getOrderByCustomerId(customerId));
//	}
	
	@GetMapping("/getOrder/{orderNumber}")
	public ResponseEntity<OrderModel> getOrder(@PathVariable("orderNumber") String orderNumber) throws OrderException{
		return ResponseEntity.ok(orderService.getOrder(orderNumber));
	}
	
	@DeleteMapping("/deleteorder/{orderNumber}")
	public ResponseEntity<Void> deleteOrder(@PathVariable("orderNumber") String orderNumber) throws OrderException {
		ResponseEntity<Void> response = null;
		OrderModel order = orderService.getOrder(orderNumber);
		if (order == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			orderService.deleteOrder(orderNumber);
			response = new ResponseEntity<>(HttpStatus.OK);
		}
		return response;
	}



}