package tn.esprit.infini2.services;

import java.util.List;

import tn.esprit.infini2.entities.Offer;

public interface IOfferService {
	List<Offer> retrieveAllOffers();

	Offer addClaim(Offer o);

	void deleteOffer(Long id);

	Offer updateOffer(Offer u);

	Offer retrieveOffer(Long id);
}
