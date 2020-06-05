package com.luv2code.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.exceptions.CustomerNotFoundException;
import com.luv2code.springdemo.exceptions.InvalidCustomerIdException;
import com.luv2code.springdemo.exceptions.InvalidCustomerPostRequest;
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
		return customer;
	}
	
	// Create a new customer
	
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer customer) {
		
		/**
		 * Two options to avoid update an existing customer:
		 * 1- Set its id to 0, it will force the creation of a new customer
		 * 2- Check if exists and return error if exists
		 */
		
		System.out.println("Customer id: " + customer.getId());
		
		// Option 1: set id to 0
		// customer.setId(0);
		
		// Option 2: throw an exception if an id value is passed
		// If JSON body does not contain id field, it's automatically assigned to 0
		if (customer.getId() != 0)
			throw new InvalidCustomerPostRequest();
		
		service.saveCustomer(customer);
		
		return customer;
	}

	private void checkIdValue(int customerId) {
		if (customerId < 1) throw new InvalidCustomerIdException();		
	}
	
	

}
