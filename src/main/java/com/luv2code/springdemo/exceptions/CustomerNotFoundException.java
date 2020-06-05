package com.luv2code.springdemo.exceptions;

public class CustomerNotFoundException extends RuntimeException {
	
	private static final String MESSAGE="User with id not found - ";
	
	public CustomerNotFoundException(String message) {
		super(message);
	}

	public CustomerNotFoundException(int id) {
		super(MESSAGE + id);
	}

}
