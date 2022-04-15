package tn.esprit.infini2.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.infini2.entities.Claim;
import tn.esprit.infini2.entities.Claim_state;
import tn.esprit.infini2.entities.CustomerAccount;
import tn.esprit.infini2.repositories.ClaimRepository;
import tn.esprit.infini2.repositories.CustomerAccountRepository;
@Service
public class ClaimServicelmpl  implements IClaimService{
	@Autowired
	ClaimRepository claimRepository;
	@Autowired
	Iemailservice emailservice;
	@Autowired
	CustomerAccountRepository customerAccountRepository;
	
////////////////////////////////////add claim///////////////////////////////////////	
	public Claim addClaim(Claim c,Long id) {
		CustomerAccount ca=customerAccountRepository.findById(id).get();
		Claim_state state=Claim_state.valueOf("Untreated");
		c.setClaim_state(state);
		c.setClaim_visibility(true);
		c.setCustomerClaim(ca);
		Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, 1);
	    Date d = cal.getTime();
	    c.setClaim_date(d);
		claimRepository.save(c);
		return c;
	}
//////////////////////////////////update claim////////////////////////////////////////
	@Override
	public Claim updateClaim(Claim u) {
		u.setClaim_visibility(u.getClaim_visibility());
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
	
////////////////////////////////retrieve claim non visible///////////////////////////////
	public List<Claim> retrieveclaim_par_client(Long idclient) {
		return null;
	//return (List<Claim>) claimRepository.Retrieve_claim_par_idclient(idclient);
	}
	
////////////////////////////////////traiter claim/////////////////////////////////////
	public Claim treated_claim(Long id,String body) {//,EmailMessage email
	Claim c=claimRepository.findById(id).get();
	Claim_state cl=Claim_state.valueOf("Treated");
	c.setClaim_state(cl);
	CustomerAccount ca=c.getCustomerClaim();
	updateClaim(c);
	emailservice.sendEmail(ca.getCustomer().getEmail(),"claim treatment",body);
	return c;
	}
////////////////////////////////////Ongoing claim/////////////////////////////////////
	public Claim ongoing_claim(Long id) {
	Claim c=claimRepository.findById(id).get();
	Claim_state cl=Claim_state.valueOf("Ongoing");
	c.setClaim_state(cl);
	CustomerAccount ca=c.getCustomerClaim();
	updateClaim(c);
	emailservice.sendEmail(ca.getCustomer().getEmail(), "Traitement en cours","Bonjour Monsieur/Madame,\r\n"
			+ "\r\n"
			+"Nous sommes en train de traiter votre reclamation,\r\n"
			+ "\r\n"
			+ "Merci de votre patience,\r\n"
			+ "\r\n"
			+ "Cordialement,\r\n"
			+ "\r\n"
			+ "Oumayma Bichiou");
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

////////////////////////////////////statistics/////////////////////////////////////////////
	
                ////////////////////stat_claim_contrat/////////////////////////////

@Override
public Map<String, Double> stat_claim_contrat(Long year) {
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
				if(Double.parseDouble(new SimpleDateFormat("yyyy").format(claims.get(i).getClaim_date()))==year)
				{
					if(claims.get(i).getClaim_contrat_type().toString()=="automobile")
					{
						nb1=nb1+1;
						System.out.println("v:"+nb1);

					}
					if(claims.get(i).getClaim_contrat_type().toString()=="vie")
					{
						nb2=nb2+1;
					}
					if(claims.get(i).getClaim_contrat_type().toString()=="Habitation")
					{
						nb3=nb3+1;
						System.out.println("habitation:"+nb3);

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

			System.out.println(nbrparcontrat);

			return nbrparcontrat;
		}
                      ////////////////////statis_par_state/////////////////////////////

@Override
public Map<String, Double> statis_par_state(Long year) {
	Map<String, Double> nbrparstate= new HashMap<String, Double>();
//Treated,Ongoing,Untreated;
	Double nb1=(double) 0;
	Double nb2=(double) 0;
    Double nb3=(double) 0;
	List<Claim> claims=claimRepository.Retrievevisibility(true);
	int nbrtot=claimRepository.Nbclaimselonvisibility(true);
	for(int i=0;i<nbrtot;i++)
	{if(Double.parseDouble(new SimpleDateFormat("yyyy").format(claims.get(i).getClaim_date()))==year) {
		if(claims.get(i).getClaim_state().toString()=="Treated")
		{
			nb1=nb1+1;
		}
		if(claims.get(i).getClaim_state().toString()=="Ongoing")
		{
			nb2=nb2+1;
		}
		if(claims.get(i).getClaim_state().toString()=="Untreated")
		{
			nb3=nb3+1;
		}
	}}
	nbrparstate.put("Treated",nb1);
	nbrparstate.put("Ongoing",nb2);
	nbrparstate.put("Untreated",nb3);
	

	return nbrparstate;
}
                        ////////////////statis_par_type////////////////


public Map<String, Double> statis_par_type(Long year) {
	Map<String, Double> nbrparstate= new HashMap<String, Double>();
//Reclamation,Complaint
	Double nb1=(double) 0;
	Double nb2=(double) 0;
	List<Claim> claims=claimRepository.Retrievevisibility(true);
	int nbrtot=claimRepository.Nbclaimselonvisibility(true);
	for(int i=0;i<nbrtot;i++)
	{if(Double.parseDouble(new SimpleDateFormat("yyyy").format(claims.get(i).getClaim_date()))==year) {
		if(claims.get(i).getClaim_type().toString()=="Reclamation")
		{
			nb1=nb1+1;
		}
		if(claims.get(i).getClaim_type().toString()=="Complaint")
		{
			nb2=nb2+1;
		}
	}
	}
	nbrparstate.put("Reclamation",nb1);
	nbrparstate.put("Complaint",nb2);	

	return nbrparstate;
	}
                    /////////////////statistics pourcentage////////////////////////////////
public Map<String, Double> statis_map_pour(Map<String, Double> map) {
    for (Iterator<Entry<String, Double>> iterator = map.entrySet().iterator(); iterator.hasNext();) {
		Entry<String, Double> mapentry = iterator.next();
		double pour=calculate_pour(mapentry.getValue());
		map.put(mapentry.getKey(),pour);
	}
    return map;
	
}
////////////////////////////////retrieve claims par id///////////////////////////////////////

public String retrieve_par_id(Long idclient) 
{
	List<Claim>claims=claimRepository.Retrievevisibility(true);
	CustomerAccount ca=customerAccountRepository.findById(idclient).get();
	List<CustomerAccount> customers = new ArrayList<>();
	for(int i = 0;i<claims.size();i++)
	{customers.add(claims.get(i).getCustomerClaim());}
    System.out.println("wsol lhne");
	int nbr=claims.size();
    System.out.println("wsol lhne zeda");
    System.out.println(nbr);
    if(customers.contains(ca)==false)
		return "vous n'avez fait aucune reclamation";
    else 
    {	String s = "voici la liste de vos reclamations :\r\n";
	    for(int i=0;i<nbr;i++) 
			{
				if(claims.get(i).getCustomerClaim()==ca)
				
					s+=claims.get(i).toString()+"\r\n";
			}
	
	return s;
    }
    
}
public String retrieve_les_reclamations_untreated() {
	List<Claim> claims=claimRepository.Retrievevisibility(true);
	String s="voici la liste des reclamations non traites :\r\n";
	for(int i=0;i<claims.size();i++)
	{   Claim c=claims.get(i);
		if(c.getClaim_state().toString()=="Untreated") 
		{
			s+=c.toString()+"\r\n";
			
		}
		
	}
	return s;
}
}