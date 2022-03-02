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
		
		@Override
		public List<Offer> retrieveAllOffers() {
			
			return (List<Offer>) offerRepository.findAll();
		}
		
		@Override
		public Offer addClaim(Offer o) {
			offerRepository.save(o);
			return o;
		}
		
		@Override
		public void deleteOffer(Long id) {
			offerRepository.deleteById(id);
		}
		
		@Override
		public Offer updateOffer(Offer u) {
			offerRepository.save(u);
			return u;
		}
		
		@Override
		public Offer retrieveOffer(Long id) {
			return offerRepository.findById(id).orElse(null);
		}
}
