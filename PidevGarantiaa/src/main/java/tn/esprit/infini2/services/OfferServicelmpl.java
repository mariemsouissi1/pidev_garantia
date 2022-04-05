package tn.esprit.infini2.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.infini2.entities.Offer;
import tn.esprit.infini2.repositories.OfferRepository;
@Slf4j


@Service
public class OfferServicelmpl implements IOfferService {
	private static final Logger log = LoggerFactory.getLogger(CreditServicelmpl.class);
		@Autowired
		OfferRepository offerRepository;
		
		
/////////////////////////////////////////add offer/////////////////////////////////////////////	
		@Override
		public Offer addOffer(Offer o) {
			o.setOffer_visibility(true);
			offerRepository.save(o);
			return o;
		}
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
}
