package com.luv2code.springdemo.exceptions;

public class CustomerNotFoundException extends RuntimeException {
	
	public CustomerNotFoundException(String message) {
		super(message);
	}

}
