package tn.esprit.infini2.services;

import java.util.Date;
import java.util.List;

import tn.esprit.infini2.entities.Offer;

public interface IOfferService {
	List<Offer> retrieveAllOffers();

	Offer addOffer(Offer o);

	void deleteOffer(Long id);

	Offer updateOffer(Offer u);

	Offer retrieveOffer(Long id);

	Offer desarchiverOffer(Long offerId);

	Offer archiverOffer(Long offerId);

	Long  calculer_age(Date datebirth);

	Long return_Offer_id(Offer u);
	Offer match_offer(Long idoffer) ;
}
