package tn.esprit.services;
import java.util.ArrayList;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.entities.Credit;
import tn.esprit.entities.CustomerAccount;
import tn.esprit.entities.Fidelite;
import tn.esprit.entities.StatutCredit;
import tn.esprit.entities.StatutCustomer;
import tn.esprit.entities.TraitementCredit;
import tn.esprit.entities.VerificationCredit;
import tn.esprit.repositories.CreditRepository;
import tn.esprit.repositories.CustomerAccountRepository;

@Slf4j
@Service
public class CreditServicelmpl implements ICreditService {
	

	private static final Logger log = LoggerFactory.getLogger(CreditServicelmpl.class);
	@Autowired
	CreditRepository creditRepository;
	
	@Autowired
	CustomerAccountRepository customerAccountRepository;
	
	
	@Autowired
	IEmailServiceCredit iEmailService;
	
//////////////////////////////////////REQUEST CREDIT PER CUSTOMER///////////////////////////////////////////////////////
	
	@Override
	public Credit requestCredit(Credit c, long idCustomerAccount) {
		log.info("Inside request credit");
		CustomerAccount ca = new CustomerAccount();
		ca = customerAccountRepository.findById(idCustomerAccount).get();
		c.setCustomerCredit(ca);
		c.setTraitementCredit(TraitementCredit.inprogress);
		c.setStatutCredit(StatutCredit.Null);
		return creditRepository.save(c);
	}

/////////////////////////////////////////VERIFICATION CREDIT////////////////////////////////////////////
	@Override
	public boolean confirmCredit(Credit c) {
			log.info("Inside confirmation credit");
			int cond1=0;
			int cond2=0;
			int cond3=0;
			List<Credit> allCredits=new ArrayList<>();
			StatutCredit [] statutcredit = StatutCredit.values(); 
			allCredits= (List<Credit>) creditRepository.findAll();
			for(Credit a : allCredits){
				for (StatutCredit s : statutcredit) {
					if (s.toString().toUpperCase() == "Ongoing".toUpperCase() ){
				 	if(a.getAmountCredit()<0.3*a.getCustomerCredit().getSalaryCustomer())
				 	{
				 		cond1=1;
				 	}
				 	 if(a.getAmountRemainingCredit()<0.1*a.getCustomerCredit().getSalaryCustomer())
				 	 {
				 		 cond2=1;
				 	 }
				 	int nbCredit = creditRepository.getCountCreditPerCustomer(c.getCustomerCredit().getIdCustomerAccount());
				 	if(nbCredit<3)
				 	 {
				 		 cond3=1;
				 	 }
				}
				}
			}
			StatutCustomer [] statutcustomer = StatutCustomer.values();
			Fidelite [] fidelite = Fidelite.values();
			for (StatutCustomer s : statutcustomer) {
			if ( s.toString().toUpperCase() == "VIP".toUpperCase() ) {
				c.setTraitementCredit(TraitementCredit.treated);
				c.setVerificationCredit(VerificationCredit.Accepted);
				c.setStatutCredit(StatutCredit.Ongoing);
				c.setAmountRemainingCredit(c.getAmountCredit());
				updateCredit(c.getIdCredit(),c);
				return true;
			}
			
			if (s.toString().toUpperCase() == "Active".toUpperCase()) {
				for (Fidelite f : fidelite) {
				if ( f.toString().toUpperCase() == "Fidele" && cond1==1 && cond2==1 && cond3==1){
					c.setTraitementCredit(TraitementCredit.treated);
					c.setStatutCredit(StatutCredit.Ongoing);
					c.setVerificationCredit(VerificationCredit.Accepted);
					c.setAmountRemainingCredit(c.getAmountCredit());
					updateCredit(c.getIdCredit(),c);
					return true;
				}
				else {
					c.setTraitementCredit(TraitementCredit.treated);
					c.setVerificationCredit(VerificationCredit.Rejected);
					c.setStatutCredit(StatutCredit.Denied);
					updateCredit(c.getIdCredit(),c);
					return false;
				}
				}
				}
			if (s.toString().toUpperCase() == "Inactive".toUpperCase()) {
				c.setTraitementCredit(TraitementCredit.treated);
				c.setVerificationCredit(VerificationCredit.Rejected);
				c.setStatutCredit(StatutCredit.Denied);
				updateCredit(c.getIdCredit(),c);
				return false;
			}
		}
		return false;
	}
	
/////////////////////UPDATE WITH CONTROL///////////////////////////////////////////
	
	@Override
	public Credit updateCredit(long idCredit, Credit c)
	{
		log.info("Inside update credit");
		if (creditRepository.findById(idCredit).isPresent()){
				Credit credit = creditRepository.findById(idCredit).get();
				c.setAmountCredit(credit.getAmountCredit());
				c.setStartDate(credit.getStartDate());
				c.setCustomerCredit(credit.getCustomerCredit());
				c.setIdCredit(credit.getIdCredit());
				c.setTransactions(credit.getTransactions());
				c.setTraitementCredit(credit.getTraitementCredit());
				c.setVerificationCredit(credit.getVerificationCredit());
				c.setStatutCredit(credit.getStatutCredit());
				
				return creditRepository.save(c);
		}
		else
		{
				log.info("Credit not found");
				return null;
		}
	}
	
//////////////////////////GET ALL CREDITS ////////////////////////

	@Override
	public List<Credit> retrieveAllCredit() {
		List <Credit> credits = (List<Credit>) creditRepository.findAll();
		for (Credit c : credits) {
			log.info("List of credits : " + c);
		}
		return credits;
	}
	
//////////////////////////GET CREDIT BY ID CREDIT/////////////////////////////

	@Override
	public Credit retrieveCredit(long idCredit) {
		return creditRepository.findById(idCredit).orElse(null);
	}
	
///////////////////GET ALL CREDITS BY ID CUSTOMER////////////////////////////////////

	
	@Override
	public List<Credit> retrieveCreditsByCustomerAccountId(long idCustomerAccount) {
		List<Credit> credits = (List<Credit>) creditRepository.findByCustomerAccountIdAllCredits(idCustomerAccount);
		return credits;
	}
	
	
///////////////////DELETE WITH CONTROL//////////////////////////////////

	@Override
	public void deleteCredit(long idCredit) {
			Credit c = creditRepository.findById(idCredit).get();	
				if (c.getStatutCredit().toStringSc() == "Paid" || c.getStatutCredit().toStringSc() == "Null" ||c.getStatutCredit().toStringSc() == "Denied"  ) {
					log.info("inside delete");
					creditRepository.deleteById(idCredit);		
				}
		
				else {
					log.error("Unpaid Credit");
					iEmailService.sendEmailUnpaidCredit(c.getCustomerCredit().getEmailAccount(), c);
				}
	}
////////////////////////////CLOSE CREDIT///////////////////////////////////////
	
	@Override
	public boolean closeCredit(long idCredit) {
			Credit c = creditRepository.findById(idCredit).get();	
				if (c.getAmountRemainingCredit() ==0 && c.getTraitementCredit().toStringTraitement() == "treated" && c.getVerificationCredit().toStringVerif() == "Accepted" ) {
					c.setStatutCredit(StatutCredit.Paid);
					updateCredit(c.getIdCredit(),c);
					creditRepository.deleteById(idCredit);	
					return true;
				}
		
				else {
					log.error("Unpaid Credit");
					return false;
				}
				
	}
	}
