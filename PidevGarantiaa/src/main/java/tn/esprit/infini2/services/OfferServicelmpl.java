package tn.esprit.infini2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.infini2.entities.Offer;
import tn.esprit.infini2.repositories.OfferRepository;

@Service
public class OfferServicelmpl implements IOfferService {
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
	
}
