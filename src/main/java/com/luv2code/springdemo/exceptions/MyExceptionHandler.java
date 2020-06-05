package com.luv2code.springdemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class MyExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<ErrorJson> invalidId (InvalidCustomerIdException ex) {
		
		ErrorJson error = new ErrorJson(
								HttpStatus.BAD_REQUEST.value(),
								ex.getMessage(),
								System.currentTimeMillis());
		
		return new ResponseEntity<ErrorJson>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorJson> customerNotFound (CustomerNotFoundException ex) {
		
		ErrorJson error = new ErrorJson(
								HttpStatus.NOT_FOUND.value(),
								ex.getMessage(),
								System.currentTimeMillis());
		
		return new ResponseEntity<ErrorJson>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorJson> invalidParam (MethodArgumentTypeMismatchException ex) {
		
		ErrorJson error = new ErrorJson(
								HttpStatus.BAD_REQUEST.value(),
								"Bad format parameter exception. " + ex.getMessage(),
								System.currentTimeMillis());
		
		return new ResponseEntity<ErrorJson>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorJson> genericException (Exception ex) {
		
		ErrorJson error = new ErrorJson(
								HttpStatus.BAD_REQUEST.value(),
								ex.getMessage(),
								System.currentTimeMillis());
		
		return new ResponseEntity<ErrorJson>(error, HttpStatus.BAD_REQUEST);
	}	
}

































