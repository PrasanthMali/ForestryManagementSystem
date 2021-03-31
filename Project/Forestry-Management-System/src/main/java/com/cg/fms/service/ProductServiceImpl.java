package com.cg.fms.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fms.dao.ProductDao;
import com.cg.fms.exception.ProductException;
import com.cg.fms.model.ProductModel;

@Service
public class ProductServiceImpl implements IProductService{

	@Autowired
	private ProductDao productRepo;
	
	@Autowired
	private EMParser parser;
	
	
	public ProductServiceImpl(ProductDao productRepo) {
		super();
		this.productRepo = productRepo;
		this.parser =new EMParser();
	}
	
	

	public ProductDao getProductRepo() {
		return productRepo;
	}



	public void setProductRepo(ProductDao orderRepo) {
		this.productRepo = orderRepo;
	}



	public EMParser getParser() {
		return parser;
	}



	public void setParser(EMParser parser) {
		this.parser = parser;
	}


	@Override
	public ProductModel getProductByProductId(String productId) throws ProductException {
		if (!productRepo.existsById(productId))
			throw new ProductException("No product found for the given Id");
		return parser.parse(productRepo.findById(productId).get());
	}
	
//	@Override
//	public List<ProductModel> getProductByContractNumber(String contractNumber) throws ProductException {
////		return contractRepo.findByCustomerId(customerId);
//		List<Product> product = productRepo.findAll();
//		List<ProductModel> productContract=new ArrayList<>();
//		Iterator<Product> cust = product.stream().iterator();
//		while(cust.hasNext()) {
//			Product val= productRepo.findById(cust.next().getProductId()).orElse(null);
//			
//			System.out.println(val.getContract().getContractNumber());
//			if(val.getContract().getContractNumber().equals(contractNumber)) {
//				System.out.println(val);
//				productContract.add(parser.parse(val));
//				System.out.println(productContract);
//			}
//		}
//		System.out.println(productContract+" "+productContract.size());
//		return productContract;
//	}

	@Override
	public ProductModel addProduct(ProductModel expected) throws ProductException{
		if ( expected!= null) {
			if (productRepo.existsById(expected.getProductId())) {
				throw new ProductException("Product with this id already exists");
			}

			expected = parser.parse(productRepo.save(parser.parse(expected)));
		}

		return expected;
	}
	@Override
	public ProductModel updateProduct(ProductModel productModel) {
		if (productModel != null) {
			if (productRepo.existsById(productModel.getProductId())) {
				productModel = parser.parse(productRepo.save(parser.parse(productModel)));
			}
			
		}
		return productModel;
	}

	@Override
	public void deleteProduct(String productId) {
		productRepo.deleteById(productId);
	}
	@Override
	public List<ProductModel> getAllProducts() {
		return productRepo.findAll().stream().map(parser::parse).collect(Collectors.toList());
	}



	

}
