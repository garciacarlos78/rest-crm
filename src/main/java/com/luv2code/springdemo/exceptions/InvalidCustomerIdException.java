package com.luv2code.springdemo.exceptions;

public class InvalidCustomerIdException extends RuntimeException {
	
	public InvalidCustomerIdException() {
		super("Invalid customerId format. Must be an int value from 1 to " + Integer.MAX_VALUE);
	}

}
