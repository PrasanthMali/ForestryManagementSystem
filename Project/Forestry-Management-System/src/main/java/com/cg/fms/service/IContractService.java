package com.cg.fms.service;

import java.util.List;

import com.cg.fms.exception.ContractException;
import com.cg.fms.model.ContractModel;

public interface IContractService {
//	
//	public List<ContractModel> getContractByCustomerId(String customerId) throws ContractException;
	public ContractModel updateContract(ContractModel contractModel)throws ContractException ;
	public void deleteContract(String contractNumber);
	public List<ContractModel> getAllContracts();
	public ContractModel getContarctByContractNumber(String contractNumber) throws ContractException;
	ContractModel addContract(ContractModel contract) throws ContractException;
	
}
