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
import com.cg.fms.exception.ContractException;
import com.cg.fms.model.ContractModel;
import com.cg.fms.service.IContractService;

@RestController
@RequestMapping(path="/contracts")
public class ContractAPI {
	
	@Autowired
	private IContractService contractService;
//	@GetMapping("/getContarctByCustomerId/{customerId}")
//	public ResponseEntity<List<ContractModel>> getContractByCustomerId(@PathVariable("customerId") String customerId) throws  ContractException{
//		return ResponseEntity.ok(contractService.getContractByCustomerId(customerId));
//	}
	
	@GetMapping("/getContarctByContractNumber/{contractNumber}")
	public ResponseEntity<ContractModel> getContarctByContractNumber(@PathVariable("contractNumber") String contractNumber) throws  ContractException{
		return ResponseEntity.ok(contractService.getContarctByContractNumber(contractNumber));
	}
	
	@GetMapping("/getallContracts")
	public ResponseEntity<List<ContractModel>> getAll() throws ContractException{
		return ResponseEntity.ok(contractService.getAllContracts());
	}
	
	@PostMapping("/addcontract")
	public ResponseEntity<ContractModel> addAdmin(@RequestBody ContractModel contract) throws ContractException {
		contract = contractService.addContract(contract);
		return new ResponseEntity<>(contract, HttpStatus.CREATED);
	}
	
	@PutMapping("/updatecontract/{contractNumber}")
	public ResponseEntity<ContractModel> updateContract(@RequestBody ContractModel contractModel,@PathVariable("contractNumber")String contractNumber) throws ContractException{
		contractModel = contractService.updateContract(contractModel);
		return new ResponseEntity<>(contractModel, HttpStatus.OK);
	}
	
	@DeleteMapping("/deletecontract/{contractNumber}")
	public ResponseEntity<String> deleteContract(@PathVariable("contractNumber") String contractNumber) throws ContractException {
		ResponseEntity<String> response = null;
		ContractModel contract = contractService.getContarctByContractNumber(contractNumber);
		if (contract == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			contractService.deleteContract(contractNumber);
			response = new ResponseEntity<>("Contract is deleted successsfully", HttpStatus.OK);
		}
		return response;
	}
}
