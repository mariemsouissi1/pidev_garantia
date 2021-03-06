package tn.pidev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.pidev.entities.CustomerAccount;
import tn.pidev.services.ICustomerAccountService;

@RestController
@RequestMapping("/customer")
public class CustomerAccountController {
		@Autowired
		ICustomerAccountService customerService;

	///////////////////////////////////////////ADD/////////////////////////////////////////	
		
		// http://localhost:8087/pidevmariem/customer/add-customer-account
			@PostMapping("/add-customer-account")
			@ResponseBody
			public CustomerAccount addCustomer(@RequestBody CustomerAccount ca)
			{
				return customerService.addCustomerAccount(ca);
			
			}
}
