package tn.esprit.infini2.services;

import java.util.Date;
import java.util.List;

import tn.esprit.infini2.entities.Sinister;

public interface ISinisterService {
List<Sinister> retrieveAllSinisters();
	
	
	public Sinister addSinister(Sinister sinister ) ;
	
	
	 String deleteSinister(Integer idSinister);
	 
	 
	 String updateSinister(Sinister sinister , Integer idSinister);
	 
	 
	 Sinister retrieveSinister(Integer id);
	 

	 List<Sinister> retrieveSinisterByEmployee(Long idEmployee);
	 
	 
	 List<Sinister> retrieveSinisterByCustomer(Long idCustomer);
	 
	 
	 List<Sinister> retrieveSinisterByDeclarationDate(Date from , Date to );
	 
	 
	 List<Sinister> retrieveSinisterBySinisterDate(Date from , Date to );
	 
     
 	public String traiterSinister( Integer idSinister) throws Exception;
 	
 	
 	public String verificationSinisterDelay(Integer idSinister);
 	
 	
 	 String suivreSinistre(Integer idSinister);
 	 
 	 
 	String CalculRapiditéReglementSinistre( Integer idSinister) ;
 	
 	/*public void affectContractToSinister(Integer idSinister,Long idContrat);*/
 	
 	
    
    
    
    
   
}
