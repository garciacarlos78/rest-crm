package com.luv2code.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.exceptions.CustomerNotFoundException;
import com.luv2code.springdemo.exceptions.InvalidCustomerIdException;
import com.luv2code.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CRMRestController {
	
	@Autowired
	private CustomerService service;
	
	@GetMapping("/customers")
	public List<Customer> getAll() {
		return service.getCustomers();
	}
	
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {
		checkIdValue(customerId);
		Customer customer = service.getCustomer(customerId);
		if (customer==null) 
			throw new CustomerNotFoundException("User with id not found - " + customerId);
		return service.getCustomer(customerId);
	}

	private void checkIdValue(int customerId) {
		if (customerId < 1) throw new InvalidCustomerIdException();		
	}

}
