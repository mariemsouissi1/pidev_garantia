package tn.esprit.infini2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

import tn.esprit.infini2.entities.Credit;
import tn.esprit.infini2.services.ICreditService;
import tn.esprit.infini2.services.IEmailServiceCredit;



@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/credit")
public class CreditController {
	@Autowired
	ICreditService CreditService;
	@Autowired 
	IEmailServiceCredit iEmailService; 
	
//////////////////////////////////////REQUEST CREDIT PER CUSTOMER///////////////////////////////////////////////////////
	
	@PreAuthorize("hasAuthority(@userService.Customer())")
	// http://localhost:8087/PIDEV_GARANTIA/credit/request-credit-id/idCustomerAccount
	@PostMapping("/request-credit-id/{id-customer}")
	@ResponseBody
	public Credit requestCreditCustomer(@RequestBody Credit c, @PathVariable("id-customer") long idCustomerAccount)
	{
	Credit credit = CreditService.requestCredit(c, idCustomerAccount);
	iEmailService.sendEmailRequest(credit.getCustomerCredit().getEmailAccount(), credit);
	return credit;
	}
	
/////////////////////////////////////////VERIFICATION CREDIT////////////////////////////////////////////
	
	@PreAuthorize("hasAuthority(@userService.Employee())")
	// http://localhost:8087/PIDEV_GARANTIA/credit/confirm-credit/1/1
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
	
	@PreAuthorize("hasAuthority(@userService.Employee())")
	// http://localhost:8087/PIDEV_GARANTIA/credit/update-credit/idCredit
	@PutMapping("/update-credit/{idCredit}")
	@ResponseBody
	public Credit refreshCredit(@RequestBody Credit c,@PathVariable("idCredit") long idCredit)
	{
		return CreditService.updateCredit(idCredit,c);
	}
	
	
//////////////////////////GET ALL CREDITS ////////////////////////
	@PreAuthorize("hasAuthority(@userService.Employee(),@userService.Customer())")
	// http://localhost:8087/PIDEV_GARANTIA/credit/retrieve-all-credits/
	@GetMapping("/retrieve-all-credits")
	@ResponseBody
	public List<Credit> retrieveAllCredits (){
		return CreditService.retrieveAllCredit();
		}
	
//////////////////////////GET CREDIT BY ID CREDIT/////////////////////////////
	@PreAuthorize("hasAuthority(@userService.Employee(),@userService.Customer())")
	// http://localhost:8087/PIDEV_GARANTIA/credit/retrieve-credit/3
	@GetMapping("/retrieve-credit/{id-credit}")
	@ResponseBody
	public Credit retrieveCredit(@PathVariable("id-credit") long idCredit){
		return CreditService.retrieveCredit(idCredit);
	}

///////////////////GET ALL CREDITS BY ID CUSTOMER////////////////////////////////////
	@PreAuthorize("hasAuthority(@userService.Employee())")
	// http://localhost:8087/PIDEV_GARANTIA/credit/retrieve-credit-customer-id/1
	@GetMapping("/retrieve-credit-customer-id/{id-customer}")
	@ResponseBody
	public List<Credit> retrieveCreditByCustomer(@PathVariable("id-customer") long idCustomerAccount){
		return CreditService.retrieveCreditsByCustomerAccountId(idCustomerAccount);
	}

///////////////////DELETE WITH CONTROL//////////////////////////////////
	@PreAuthorize("hasAuthority(@userService.Employee())")
	// http://localhost:8087/PIDEV_GARANTIA/credit/supp-credit/3
	@DeleteMapping("/supp-credit")
	@ResponseBody
	public void suppCredit(@RequestParam long idCredit){
		CreditService.deleteCredit(idCredit);
		}
	
////////////////////////////CLOSE CREDIT///////////////////////////////////////

	// http://localhost:8087/PIDEV_GARANTIA/credit/close-credit/1
	@PreAuthorize("hasAuthority(@userService.Employee())")
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

