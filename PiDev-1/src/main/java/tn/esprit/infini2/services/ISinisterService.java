package tn.esprit.infini2.services;

import java.util.Date;
import java.util.List;

import tn.esprit.infini2.entities.Sinister;

public interface ISinisterService {
	public Sinister addSinister(Sinister sinister ) ;
	String deleteSinister(int id);
	String updateSinister(Sinister sinister , int idSinister);
	Sinister retrieveSinister(int id);
	List<Sinister> retrieveSinisterByContract(int contractId);
    Sinister retrieveSinisterByDeclarationDate(Date from , Date to );
	Sinister retrieveSinisterBySinisterDate(Date from , Date to );
	Sinister declarerSinistre(Sinister sinister , int contractId  ) ;
	 //,  bech ne5Ou insuredId 
	 
    Sinister affecterContratSinister(int idContrat,int idSinister );
	String traiterSinistre(int siniterId ) throws Exception;
	String CalculRapiditéReglementSinistre( int sinisterId) ;
	String CalculRatioRejetDemIndemn( int sinisterId) ;
    String CalculCoutMoyen(int insurerId) ;
	String verifierDelai(int sinisterId) ;
	String envoyerMailToEmployee(int employeeId) ;
	String envoyerMailToCustomer(int customerId);
	String suivreSinistre(int sinisterId);
	String calculSinistrWallet(int customerId) ; 
	double verifierFacture(int sinisterId )  ;
	String payerSinistre(int sinisterId);
	List<Sinister> retrieveAllSinisters();
	 List<Sinister> retrieveSinisterByEmployee(int employeeId);
	 List<Sinister> retrieveSinisterByCustomer(int customerId);
	   
	 

}
