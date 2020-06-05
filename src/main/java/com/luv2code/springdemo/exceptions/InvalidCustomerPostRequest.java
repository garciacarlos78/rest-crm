package com.luv2code.springdemo.exceptions;

public class InvalidCustomerPostRequest extends RuntimeException {
	
	private static final String MESSAGE = 
			"Invalid POST usage. Customer id is automatically generated. You must not include" +
			" that field, assign to it value 0, or send PUT method if you want to update an existing" +
			" customer.";

	public InvalidCustomerPostRequest() {
		super(MESSAGE);
	}

	public InvalidCustomerPostRequest(String arg0) {
		super(arg0);
	}

	public InvalidCustomerPostRequest(Throwable arg0) {
		super(arg0);
	}

	public InvalidCustomerPostRequest(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public InvalidCustomerPostRequest(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
