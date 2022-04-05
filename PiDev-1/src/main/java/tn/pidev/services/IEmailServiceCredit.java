package tn.pidev.services;

import tn.pidev.entities.Credit;
import tn.pidev.entities.TransactionCredit;

public interface IEmailServiceCredit {
	
	void sendEmailRequest(String email, Credit c);
	
	void sendEmailRejet(String email, Credit c);
	
	void sendEmailAcceptance(String email, Credit c);
	
	void sendEmailUnpaidCredit(String email, Credit c);
	
	void sendEmailUnverifiedTransaction(String email, TransactionCredit t);
	
	void sendEmailwithAttachment(); 
	
	int generateCode();
}
