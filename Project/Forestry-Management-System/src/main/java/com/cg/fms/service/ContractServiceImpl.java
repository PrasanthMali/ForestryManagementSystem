package com.cg.fms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fms.dao.ContractDao;
import com.cg.fms.exception.ContractException;
import com.cg.fms.model.ContractModel;
@Service
public class ContractServiceImpl implements IContractService{
	
	@Autowired
	private ContractDao contractRepo;
	
	@Autowired
	private EMParser parser;
	
	
	public ContractServiceImpl(ContractDao contractRepo) {
		super();
		this.contractRepo = contractRepo;
		this.parser =new EMParser();
	}
	
	

	public ContractDao getContractRepo() {
		return contractRepo;
	}



	public void setContractRepo(ContractDao contractRepo) {
		this.contractRepo = contractRepo;
	}



	public EMParser getParser() {
		return parser;
	}



	public void setParser(EMParser parser) {
		this.parser = parser;
	}
	@Override
	public ContractModel getContarctByContractNumber(String contractNumber) throws ContractException {
		if (!contractRepo.existsById(contractNumber))
			throw new ContractException("No Contract found for the given Id");
		return parser.parse(contractRepo.findById(contractNumber).get());
	}


//	@Override
//	public List<ContractModel> getContractByCustomerId(String customerId) throws ContractException {
//	List<Contract> contract=contractRepo.findAll();
//	List<ContractModel> contractCustomer=new ArrayList<>();
//	Iterator<Contract> cust = contract.stream().iterator();
//	while(cust.hasNext()) {
//		Contract val=contractRepo.findById(cust.next().getContractNumber()).orElse(null);
//		if(val.getCustomer().getCustomerId().equals(customerId)) {
//			contractCustomer.add(parser.parse(val));
//			System.out.println(contractCustomer);
//		}
//	}
//	return contractCustomer;
//}

	@Override
	public ContractModel addContract(ContractModel contract) throws ContractException{
		if ( contract!= null) {
			if (contractRepo.existsById(contract.getContractNumber())) {
				throw new ContractException("Contract with this id already exists");
			}

			contract = parser.parse(contractRepo.save(parser.parse(contract)));
		}

		return contract;
	}
	@Override
	public ContractModel updateContract(ContractModel contractModel) throws ContractException {
		if (contractModel != null) {
			if (contractRepo.existsById(contractModel.getContractNumber())) {
				contractModel = parser.parse(contractRepo.save(parser.parse(contractModel)));
			}
			else {
				throw new ContractException("Contract with this id doesnot  exists");
			}
			
		}
		return contractModel;
	}

	@Override
	public void deleteContract(String contractNumber) {
		contractRepo.deleteById(contractNumber);
	}
	@Override
	public List<ContractModel> getAllContracts() {
		return contractRepo.findAll().stream().map(parser::parse).collect(Collectors.toList());
	}



}
