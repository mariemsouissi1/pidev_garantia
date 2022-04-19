package tn.esprit.services;

import tn.esprit.entities.Credit;
import tn.esprit.entities.TransactionCredit;

public interface IEmailServiceCredit {
	
	void sendEmailRequest(String email, Credit c);
	
	void sendEmailRejet(String email, Credit c);
	
	void sendEmailAcceptance(String email, Credit c);
	
	void sendEmailUnpaidCredit(String email, Credit c);
	
	void sendEmailUnverifiedTransaction(String email, TransactionCredit t);
	
	void sendEmailwithAttachment(); 
	
//	int generateCode();
}
