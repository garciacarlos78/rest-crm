# rest-crm
Create REST API to manage CRM database

Academic purposes project consisting in creating REST API functionalities from a former CRM Spring MVC project.   
The base project consists of a CRM database (MySQL), and implemented DAO and Services to interact with database.  
Base source code: http://www.luv2code.com/spring-crm-rest-demo

## Summary
Added REST API functionalities at the end of the project:
  - Get a list of customers
  - Get a single customer by id
  - Add a new customer
  - Update a customer
  - Delete a customer

## Initial set-up
Execute /sql-scripts scripts in your local database server to create project's database user and database with some data.

## GET /api/customers
Get a list with all the customers.  
Commit: *Get all*

## GET /api/customers/{customerId}
Get the customer with id *customerId*.  

Added exception handling:
  - Customer not found
  - Invalid customer id (*-Integer.MIN_VALUE* < customerId < 1)
  - Bad format parameter (customerId > *Integer.MAX_VALUE*, customerId **instanceof** String)  
  
Commit: *Get customer*  

## POST /api/customers
Create a new customer using the data sent in the JSON body.  
    
JSON structure: { "firstName" : String, "lastName" : String, "email": String }	
  
Added exception handling for introduced customer id (field *customerId* must be 0 or non existing in the request body).  
  
Commit: *Post customer*
























