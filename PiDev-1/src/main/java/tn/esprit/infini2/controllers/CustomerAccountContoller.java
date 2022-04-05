package tn.esprit.infini2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.infini2.entities.CustomerAccount;
import tn.esprit.infini2.services.ICustomerAccountService;
@RestController
@RequestMapping("/customerAccount")
public class CustomerAccountContoller {
	@Autowired
	ICustomerAccountService customerAccountService;
	// http://localhost:8085/PIDEV/customerAccount/add-customerAccount
	@PostMapping("add-customerAccount")
	@ResponseBody
	public CustomerAccount addcustomerAccount(@RequestBody CustomerAccount s)
	{
	CustomerAccount CA = customerAccountService.addCustomerAccount(s);
	return CA;
	}
}
