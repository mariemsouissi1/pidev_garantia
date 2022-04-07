package tn.pidev.services;

import java.util.List;

import tn.pidev.entities.Credit;


public interface ICreditService {
	
	List<Credit> retrieveAllCredit();

	boolean confirmCredit(Credit c);

	Credit updateCredit(long idCredit,Credit c);

	Credit retrieveCredit(long idCredit);

	Credit requestCredit(Credit c, long idCustomerAccount);

	void deleteCredit(long idCredit) ;
	
	List<Credit> retrieveCreditsByCustomerAccountId(long idCustomerAccount);
	
	boolean closeCredit(long idCredit);
}
