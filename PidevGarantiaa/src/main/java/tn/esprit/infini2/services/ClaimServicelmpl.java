package tn.esprit.infini2.services;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.infini2.entities.Claim;
import tn.esprit.infini2.entities.Claim_state;
import tn.esprit.infini2.repositories.ClaimRepository;
@Service
public class ClaimServicelmpl  implements IClaimService{
	@Autowired
	ClaimRepository claimRepository;
	@Autowired
	Iemailservice emailservice;
////////////////////////////////////add claim///////////////////////////////////////	
	@Override
	public Claim addClaim(Claim c) {
		c.setClaim_visibility(true);
		claimRepository.save(c);
		return c;
	}
//////////////////////////////////update claim////////////////////////////////////////
	@Override
	public Claim updateClaim(Claim u) {
		claimRepository.save(u);
		return u;
	}
//////////////////////////////////delete claim////////////////////////////////////////	
	@Override
	public void deleteClaim(Long id) {
		claimRepository.deleteById(id);		
	}
//////////////////////////////////retrieve historique/////////////////////////////////
	@Override
	public List<Claim> retrieveAllClaims() {
		return (List<Claim>) claimRepository.findAll();
	}
//////////////////////////////////retrieve claim by id////////////////////////////////
	@Override
	public Claim retrieveClaim(Long id) {
		return claimRepository.findById(id).orElse(null);

	}
//////////////////////////////////archiver claim//////////////////////////////////////
	
	public Claim archiverClaim(Long id) {
		Claim c=claimRepository.findById(id).get();
		c.setClaim_visibility(false);
		updateClaim(c);
		return c;
	}
//////////////////////////////////desarchiver claim///////////////////////////////////
	
	public Claim desarchiverClaim(Long id) {
		Claim c=claimRepository.findById(id).get();
		c.setClaim_visibility(true);
		updateClaim(c);
		return c;
	}
////////////////////////////////retrieve claim visible///////////////////////////////
	public List<Claim> retrievevisible() {
		return (List<Claim>) claimRepository.Retrievevisibility(true);
	}
////////////////////////////////retrieve claim non visible///////////////////////////////
	public List<Claim> retrievenonvisible() {
		return (List<Claim>) claimRepository.Retrievevisibility(false);
	}	
////////////////////////////////////traiter claim/////////////////////////////////////
	public Claim treated_claim(Long id,String body) {//,EmailMessage email
	Claim c=claimRepository.findById(id).get();
	Claim_state cl=Claim_state.valueOf("Treated");
	c.setClaim_state(cl);
	updateClaim(c);
	emailservice.sendEmail(c.getClaim_email(),"claim treatment",body);
	return c;
	}
////////////////////////////////////Ongoing claim/////////////////////////////////////
	public Claim ongoing_claim(Long id) {
	Claim c=claimRepository.findById(id).get();
	Claim_state cl=Claim_state.valueOf("Ongoing");
	c.setClaim_state(cl);
	updateClaim(c);
	emailservice.sendEmail(c.getClaim_email(), "Ongoing treatment","Nous sommes en train de traiter votre reclamation");
	return c;
}
////////////////////////////////nbre claim par type contrat//////////////////////////////ma
	public int statis_nbr_contrat(String type){
		List<Claim> claims=claimRepository.Retrievevisibility(true);
		int nbre=0;
		int nbrtot=claimRepository.Nbclaimselonvisibility(true);
		for(int i=0;i<nbrtot;i++)
		{
			if(claims.get(i).getClaim_contrat_type().toString()==type)
			{
				nbre=nbre+1;
			}
		}
		return nbre;
	}
////////////////////////////////nbre claim par state //////////////////////////////
	public int statis_nbr_state(String state){
		List<Claim> claims=claimRepository.Retrievevisibility(true);
		int nbre=0;
		int nbrtot=claimRepository.Nbclaimselonvisibility(true);
		for(int i=0;i<nbrtot;i++)
		{
			if(claims.get(i).getClaim_state().toString()==state)
			{
			nbre=nbre+1;
			}
		}
		return nbre;
		}
////////////////////////////////nbre claim par typeclaim //////////////////////////////
	public int statis_nbr_typeclaim(String typeclaim){
	List<Claim> claims=claimRepository.Retrievevisibility(true);
	int nbre=0;
	int nbrtot=claimRepository.Nbclaimselonvisibility(true);
	for(int i=0;i<nbrtot;i++)
	{
		if(claims.get(i).getClaim_type().toString()==typeclaim)
		{
		nbre=nbre+1;
		}
	}
	return nbre;
	}
////////////////////////////////////calculate pourcentage////////////////////////
	public double calculate_pour(double nbr) {
		double pour;
		int nbrtot=claimRepository.Nbclaimselonvisibility(true);
		pour=((nbr/nbrtot)*100);
		return pour;
	}

////////////////////////////////////statistics/////////////////////////////////////////
@Override
public Map<String, Double> stat_claim_contrat() {
	Map<String, Double> nbrparcontrat = new HashMap<String, Double>();
	//	automobile,vie,Habitation,epargne,Retraite,Chefdefamille,Santé,avenirdemesenfants,materielinformatique;
			Double nb1=(double) 0;
			Double nb2=(double) 0;
		    Double nb3=(double) 0;
		    Double nb4=(double) 0;
			Double nb5=(double) 0;
			Double nb6=(double) 0;
		    Double nb7=(double) 0;
		    Double nb8=(double) 0;
		    Double nb9=(double) 0;
			List<Claim> claims=claimRepository.Retrievevisibility(true);
			int nbrtot=claimRepository.Nbclaimselonvisibility(true);
			for(int i=0;i<nbrtot;i++)
			{
				if(claims.get(i).getClaim_contrat_type().toString()=="automobile")
				{
					nb1=nb1+1;
				}
				if(claims.get(i).getClaim_contrat_type().toString()=="vie")
				{
					nb2=nb2+1;
				}
				if(claims.get(i).getClaim_contrat_type().toString()=="Habitation")
				{
					nb3=nb3+1;
				}
				if(claims.get(i).getClaim_contrat_type().toString()=="epargne")
				{
					nb4=nb4+1;
				}
				if(claims.get(i).getClaim_contrat_type().toString()=="Retraite")
				{
					nb5=nb5+1;
				}
				if(claims.get(i).getClaim_contrat_type().toString()=="Chefdefamille")
				{
					nb6=nb6+1;
				}
				if(claims.get(i).getClaim_contrat_type().toString()=="Santé")
				{
					nb7=nb7+1;
				}
				if(claims.get(i).getClaim_contrat_type().toString()=="avenirdemesenfants")
				{
					nb8=nb8+1;
				}
				if(claims.get(i).getClaim_contrat_type().toString()=="materielinformatique")
				{
					nb9=nb9+1;
				}
			}
			nbrparcontrat.put("automobile",nb1);
			nbrparcontrat.put("vie",nb2);
			nbrparcontrat.put("Habitation",nb3);
			nbrparcontrat.put("epargne",nb4);
			nbrparcontrat.put("Retraite",nb5);
			nbrparcontrat.put("Chefdefamille",nb6);
			nbrparcontrat.put("Santé",nb7);
			nbrparcontrat.put("avenirdemesenfants",nb8);
			nbrparcontrat.put("materielinformatique",nb9);

			return nbrparcontrat;
		}

@Override
public Map<String, Double> statis_par_state() {
	Map<String, Double> nbrparstate= new HashMap<String, Double>();
//Treated,Ongoing,Untreated;
	Double nb1=(double) 0;
	Double nb2=(double) 0;
    Double nb3=(double) 0;
	List<Claim> claims=claimRepository.Retrievevisibility(true);
	int nbrtot=claimRepository.Nbclaimselonvisibility(true);
	for(int i=0;i<nbrtot;i++)
	{
		if(claims.get(i).getClaim_contrat_type().toString()=="Treated")
		{
			nb1=nb1+1;
		}
		if(claims.get(i).getClaim_contrat_type().toString()=="Ongoing")
		{
			nb2=nb2+1;
		}
		if(claims.get(i).getClaim_contrat_type().toString()=="Untreated")
		{
			nb3=nb3+1;
		}
	}
	nbrparstate.put("Treated",nb1);
	nbrparstate.put("Ongoing",nb2);
	nbrparstate.put("Untreated",nb3);
	

	return nbrparstate;
}

public Map<String, Double> statis_par_type() {
	Map<String, Double> nbrparstate= new HashMap<String, Double>();
//Treated,Ongoing,Untreated;
	Double nb1=(double) 0;
	Double nb2=(double) 0;
	List<Claim> claims=claimRepository.Retrievevisibility(true);
	int nbrtot=claimRepository.Nbclaimselonvisibility(true);
	for(int i=0;i<nbrtot;i++)
	{
		if(claims.get(i).getClaim_contrat_type().toString()=="Reclamation")
		{
			nb1=nb1+1;
		}
		if(claims.get(i).getClaim_contrat_type().toString()=="Complaint")
		{
			nb2=nb2+1;
		}

	}
	nbrparstate.put("Reclamation",nb1);
	nbrparstate.put("Complaint",nb2);	

	return nbrparstate;
}
/////////////////////////////////////statistics pourcentage////////////////////////////////
public Map<String, Double> statis_map_pour(Map<String, Double> map) {
    for (Iterator<Entry<String, Double>> iterator = map.entrySet().iterator(); iterator.hasNext();) {
		Entry<String, Double> mapentry = iterator.next();
		double pour=calculate_pour(mapentry.getValue());
		map.put(mapentry.getKey(),pour);
	}
    return map;
	
}

}