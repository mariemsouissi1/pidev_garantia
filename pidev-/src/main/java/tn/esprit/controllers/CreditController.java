package tn.esprit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.entities.Credit;
import tn.esprit.services.ICreditService;
import tn.esprit.services.IEmailServiceCredit;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/credit")
public class CreditController {
	@Autowired
	ICreditService CreditService;
	@Autowired 
	IEmailServiceCredit iEmailService; 
	
//////////////////////////////////////REQUEST CREDIT PER CUSTOMER///////////////////////////////////////////////////////
	
	// http://localhost:8087/pidevmariem/credit/request-credit-id/idCustomerAccount
	@PostMapping("/request-credit-id/{id-customer}")
	@ResponseBody
	public Credit requestCreditCustomer(@RequestBody Credit c, @PathVariable("id-customer") long idCustomerAccount)
	{
	Credit credit = CreditService.requestCredit(c, idCustomerAccount);
	iEmailService.sendEmailRequest(credit.getCustomerCredit().getEmailAccount(), credit);
	return credit;
	}
	
/////////////////////////////////////////VERIFICATION CREDIT////////////////////////////////////////////
	
	// http://localhost:8087/pidevmariem/credit/confirm-credit/1/1
	@PutMapping("/confirm-credit/{id-customer}/{id-credit}")
	@ResponseBody
	public boolean verifCredit(@PathVariable("id-customer") long idCustomerAccount, @PathVariable("id-credit") long idC) {
		boolean verif=false;
		List <Credit> listcredit = CreditService.retrieveCreditsByCustomerAccountId(idCustomerAccount);
			for (Credit c : listcredit) {
				if (c.getIdCredit() == idC) {
					
				verif = CreditService.confirmCredit(c);
				if (verif==true) {
					iEmailService.sendEmailAcceptance(c.getCustomerCredit().getEmailAccount(), c);}
				else {
					iEmailService.sendEmailRejet(c.getCustomerCredit().getEmailAccount(), c);
				}
			}
		}
		return verif;

		}

/////////////////////UPDATE WITH CONTROL///////////////////////////////////////////
	
	// http://localhost:8087/pidevmariem/credit/update-credit/idCredit
	@PutMapping("/update-credit/{idCredit}")
	@ResponseBody
	public Credit refreshCredit(@RequestBody Credit c,@PathVariable("idCredit") long idCredit)
	{
		return CreditService.updateCredit(idCredit,c);
	}
	
	
//////////////////////////GET ALL CREDITS ////////////////////////
	
	// http://localhost:8087/pidevmariem/credit/retrieve-all-credits/
	@GetMapping("/retrieve-all-credits")
	@ResponseBody
	public List<Credit> retrieveAllCredits (){
		return CreditService.retrieveAllCredit();
		}
	
//////////////////////////GET CREDIT BY ID CREDIT/////////////////////////////
	
	// http://localhost:8087/pidevmariem/credit/retrieve-credit/3
	@GetMapping("/retrieve-credit/{id-credit}")
	@ResponseBody
	public Credit retrieveCredit(@PathVariable("id-credit") long idCredit){
		return CreditService.retrieveCredit(idCredit);
	}

///////////////////GET ALL CREDITS BY ID CUSTOMER////////////////////////////////////

	// http://localhost:8087/pidevmariem/credit/retrieve-credit-customer-id/1
	@GetMapping("/retrieve-credit-customer-id/{id-customer}")
	@ResponseBody
	public List<Credit> retrieveCreditByCustomer(@PathVariable("id-customer") long idCustomerAccount){
		return CreditService.retrieveCreditsByCustomerAccountId(idCustomerAccount);
	}

///////////////////DELETE WITH CONTROL//////////////////////////////////
	
	// http://localhost:8087/pidevmariem/credit/supp-credit/3
	@DeleteMapping("/supp-credit")
	@ResponseBody
	public void suppCredit(@RequestParam long idCredit){
		CreditService.deleteCredit(idCredit);
		}
	
////////////////////////////CLOSE CREDIT///////////////////////////////////////

	@PutMapping("/close-credit/{id-credit}")
	@ResponseBody
	public String closeCredit(@PathVariable("id-credit") long idC) {
		if (CreditService.closeCredit(idC) == true) {
			return "Paid Credit";
		}
		else {
			return "Unpaid Credit";
		}			
	}

}

