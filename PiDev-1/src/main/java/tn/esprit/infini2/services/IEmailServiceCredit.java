package tn.esprit.infini2.services;

import tn.esprit.infini2.entities.Credit;
import tn.esprit.infini2.entities.TransactionCredit;

public interface IEmailServiceCredit {
	
	void sendEmailRequest(String email, Credit c);
	
	void sendEmailRejet(String email, Credit c);
	
	void sendEmailAcceptance(String email, Credit c);
	
	void sendEmailUnpaidCredit(String email, Credit c);
	
	void sendEmailUnverifiedTransaction(String email, TransactionCredit t);
	
	void sendEmailwithAttachment(); 
	
	int generateCode();
}
