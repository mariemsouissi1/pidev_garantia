package tn.esprit.infini2.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.infini2.services.Iindemnity_payService;


	
	
	
	@Controller(value = "Indemnity_payController")
	public class  Indemnity_payController {
		
		
		
		@Autowired
		Iindemnity_payService Iindemnity_payService ;
		
		@Autowired
		IContractService contractService ;
		

		private int id;
		
		
		private String Indemnity_payCode;
		
		
		private Date Indemnity_payDate;
		
		
		private double amountPayed;
		
		
		private String Indemnity_payMethod;

	}

}
