package com.luv2code.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		checkValidId(customerId);
		Customer customer = service.getCustomer(customerId);
		if (customer==null) 
			throw new CustomerNotFoundException(customerId);
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

	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer customer) {
		
		// check if the given customer id corresponds to a valid customer
		checkExistingCustomerId(customer.getId());
		
		// the id is valid (otherwise a exception would've been thrown)
		service.saveCustomer(customer);
		
		return customer;
	}
	
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {
		checkExistingCustomerId(customerId);
		service.deleteCustomer(customerId);
		return "Deleted customer with id - " + customerId;
	}
	
	private void checkExistingCustomerId(int id) {
		
		// check valid id (1-MAX_INT)
		checkValidId(id);
		
		// check if customer exists, throw exception if it doesn't exist
		if (service.getCustomer(id) == null)
			throw new CustomerNotFoundException(id);		
	}

	private void checkValidId(int customerId) {
		if (customerId < 1) throw new InvalidCustomerIdException();		
	}
}






















