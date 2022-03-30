package tn.esprit.infini2.services;

import java.util.List;

import tn.esprit.infini2.entities.Indemnity_pay;

public interface Iindemnity_payService {
	
	
	List<Indemnity_pay> retrieveAllIndemnity_pay();
	
	
	List<Indemnity_pay> retrieveAllIndemnity_pay(int contractId);
	
	Indemnity_pay addIndemnity_pay(Indemnity_pay p);
	
	 void deleteIndemnity_pay(int id);
	 
	 Indemnity_pay updateIndemnity_pay(Indemnity_pay p);
	 
	 Indemnity_pay retrieveIndemnity_pay(int id);

}
