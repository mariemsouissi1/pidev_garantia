package tn.esprit.infini2.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import tn.esprit.infini2.entities.Claim;

public interface IClaimService {
	List<Claim> retrieveAllClaims();

	Claim addClaim(Claim c,Long id);

	void deleteClaim(Long id);

	Claim updateClaim(Claim u);

	Claim retrieveClaim(Long id);

	Claim archiverClaim(Long id);
	
	String retrieve_par_id(Long id);
	
	String retrieve_les_reclamations_untreated();
	Claim desarchiverClaim(Long id);

	List<Claim> retrievevisible();
	
	List<Claim> retrievenonvisible();

	int statis_nbr_contrat(String type);
		 
    int statis_nbr_state(String state);
		 
    int statis_nbr_typeclaim(String typeclaim);
		 
    Claim treated_claim(Long id,String body);
    //,EmailMessage email);
    
    Claim ongoing_claim(Long id);



	Map<String, Double> statis_par_state(Long year);
	Map<String, Double> stat_claim_contrat(Long year);
	Map<String, Double> statis_par_type(Long year);

	Map<String, Double> statis_map_pour(Map<String, Double> mAPClaims);

	List<Claim> retrieveclaim_par_client(Long idClient);
	 
	 
}
