package com.cg.fms.exception;

public class OrderException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
		
		public OrderException(String errorMessage) {
			super(errorMessage);
		}




}
