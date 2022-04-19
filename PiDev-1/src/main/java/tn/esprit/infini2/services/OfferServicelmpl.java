package tn.esprit.infini2.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.infini2.entities.CustomerAccount;
import tn.esprit.infini2.entities.Offer;
import tn.esprit.infini2.repositories.CustomerAccountRepository;
import tn.esprit.infini2.repositories.CustomerRepository;
import tn.esprit.infini2.repositories.OfferRepository;
@Slf4j


@Service
public class OfferServicelmpl implements IOfferService {
	private static final Logger log = LoggerFactory.getLogger(CreditServicelmpl.class);
		@Autowired
		OfferRepository offerRepository;
		@Autowired
		CustomerAccountRepository customerAccountRepository;
		
		@Autowired
		Iemailservice emailservice;
		private String s;
//////////////////////////////////////////update offer///////////////////////////////////////////
		@Override
		public Offer updateOffer(Offer u) {
			offerRepository.save(u);
			return u;
		}
/////////////////////////////////////////retrieve offer//////////////////////////////////////////
		@Override
		public List<Offer> retrieveAllOffers() {
			
			return (List<Offer>) offerRepository.findAll();
		}
		
		@Override
		public Offer retrieveOffer(Long id) {
			return offerRepository.findById(id).orElse(null);
		}

/////////////////////////////////////////delete offer////////////////////////////////////////////		
		@Override
		public void deleteOffer(Long id) {
			offerRepository.deleteById(id);
			log.info("Offer deleted");
		}
		
//////////////////////////////////archiver Offer//////////////////////////////////////
		
	public Offer archiverOffer(Long id) {
	Offer c=offerRepository.findById(id).get();
	c.setOffer_visibility(false);
	updateOffer(c);
	return c;
	}
//////////////////////////////////desarchiver Offer///////////////////////////////////

	public Offer desarchiverOffer(Long id) {
	Offer c=offerRepository.findById(id).get();
	c.setOffer_visibility(true);
	updateOffer(c);
	return c;
}
	
	@Override
	public Long return_Offer_id(Offer u) {
		return u.getOffer_id();
	}	
////////////////////////////////////////calculer age/////////////////////////////////////////
	
	public Long  calculer_age(Date datebirth) {
	        Date Datelyoum = new Date();
	        Long diff = (long) (Datelyoum.getYear() - datebirth.getYear());
	        System.out.println("days is : "+diff);
	        return diff;
	}
/////////////////////////////////////assign category offer///////////////////////////////////////////
public String assignOffer(CustomerAccount ca) 
{	
	Long age=calculer_age(ca.getCustomer().getBirthDate());
	String s=null;
	if(age<20) 
	{s="Inf à 20";}
	else if((age>=20)&&(age<30)) 
	{s="Entre 20 et 30";}
	else if((age>=30)&&(age<50)) 
	{s="Entre 30 et 50";}
	else 
	{s="Sup à 50";}
	return s;
	
}
////////////////////////////////////match offers///////////////////////////////////////////////
  @SuppressWarnings("null")
public Offer match_offer(Long idoffer) 
  {   
	  List<String> listSupà50=new ArrayList(){{add("Famille");add("Biens");add("Argent");add("Retraite");add("Santé");}};
	  List<String> listinfà20=new ArrayList(){{add("Biens");add("Argent");}};
	  List<String> list2030=new ArrayList(){{add("Famille");add("Biens");add("Argent");}};
	  List<String> list3050=new ArrayList(){{add("Famille");add("Biens");add("Argent");add("Santé");}};

	  Offer o= offerRepository.findById(idoffer).get();
	  List<CustomerAccount> accounts=(List<CustomerAccount>) customerAccountRepository.findAll();
	  List<CustomerAccount> customers =  new ArrayList<>();
	  int nbr=accounts.size();
		for(int i=0;i<nbr;i++)
		{
			CustomerAccount ca=accounts.get(i);
		//	  List<Offer> offers = ca.();
			String s=assignOffer(ca);
			if(s=="Inf à 20") 
			{
				if((listinfà20.contains(o.getOffer_categorie().toString()))&&(o.getExpired()==false))
				{
					customers.add(ca);
					//offers.add(o);
				}
			}
			else if(s=="Entre 20 et 30") 
			{
				if((list2030.contains(o.getOffer_categorie().toString()))&&(o.getExpired()==false))
				{
					customers.add(ca);
					//offers.add(o);
				}
			}
			else if(s=="Entre 30 et 50") 
			{
				if((list3050.contains(o.getOffer_categorie().toString()))&&(o.getExpired()==false))
				{
					customers.add(ca);
					//offers.add(o);
				}
			}
			else if(s=="Sup à 50") 
			{
				if((listSupà50.contains(o.getOffer_categorie().toString()))&&(o.getExpired()==false))
				{
					customers.add(ca);
					//offers.add(o);
				}
			}
		//ca.setOffers(offers);
      //  System.out.println("offers is : "+offers);

		}
		o.setO_customerAccount(customers);
        System.out.println("customers is : "+customers);

	return o;
	  
  }
/////////////////////////////////////////add offer/////////////////////////////////////////////	
@Override
public Offer addOffer(Offer o) {
		o.setOffer_visibility(true);
		o.setExpired(false);
		offerRepository.save(o);
		o = match_offer(o.getOffer_id());
		List<CustomerAccount> accounts=o.getO_customerAccount();
		int nbr=accounts.size();
		for(int i=0;i<nbr;i++)
		{
			emailservice.sendEmail(accounts.get(i).getCustomer().getEmail(),"Profitez de notre nouvelle offre sur-mesure","Bonjour "+accounts.get(i).getCustomer().getFirstName()+",\r\n"
					+ "\r\n"
					+ "Nous avons élargi notre gamme afin de répondre à toutes vos exigences en créant des produits qui ne sont plus standard, mais sur-mesure..\r\n"
					+ "\r\n"
					+o.getOffer_description()
					+ "\r\n"
					+ "Dans l’attente de votre réponse, je vous souhaite une excellente journée.\r\n"
					+ "\r\n"
					+ "Cordialement,\r\n"
					+ "\r\n"
					+ "Oumayma Bichiou");
		}
		offerRepository.save(o);

return o;
}
}
