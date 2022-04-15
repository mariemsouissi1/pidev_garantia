package tn.esprit.infini2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.infini2.entities.Customer;
import tn.esprit.infini2.services.ICustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerContoller {
	@Autowired
	ICustomerService customerService;
	// http://localhost:8085/PIDEV/customer/add-customer
	@PostMapping("add-customer")
	@ResponseBody
	public Customer addcustomer(@RequestBody Customer s)
	{
	Customer CA = customerService.addCustomer(s);
	return CA;
	}
}