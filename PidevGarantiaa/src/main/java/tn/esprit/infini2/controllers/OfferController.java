package tn.esprit.infini2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.infini2.entities.Offer;
import tn.esprit.infini2.entities.Offer;
import tn.esprit.infini2.services.IOfferService;

@RestController
@RequestMapping("/offer")
public class OfferController {
	@Autowired
	IOfferService offerService;

	// http://localhost:8085/PIDEV/offer/retrieve-all-Offers
	@GetMapping("/retrieve-all-Offers")
	@ResponseBody
	public List<Offer> getOffers() {
	List<Offer> listoffers = offerService.retrieveAllOffers();
	return listoffers;
	}
	// http://localhost:8085/PIDEV/offer/retrieve-Offer/2
	@GetMapping("/retrieve-Offer/{offer-id}")
	@ResponseBody
	public Offer retrieveOffer(@PathVariable("offer-id") Long idOffer) {
	return offerService.retrieveOffer(idOffer);
	}

	// http://localhost:8085/PIDEV/offer/add-Offer
	@PostMapping("/add-Offer")
	@ResponseBody
	public Offer addOffer(@RequestBody Offer s)
	{
	Offer offer = offerService.addOffer(s);
	return offer;
	}
	// http://localhost:8085/PIDEV/offer/remove-Offer/2
	@DeleteMapping("/remove-Offer/{Offer-id}")
	@ResponseBody
	public void removeOffer(@PathVariable("Offer-id") Long OfferId) {
		offerService.deleteOffer(OfferId);
	}

	// http://localhost:8085/PIDEV/offer/modify-Offer
	@PutMapping("/modify-Offer")
	@ResponseBody
	public Offer modifyOffer(@RequestBody Offer offer) {
	return offerService.updateOffer(offer);
	}
	// http://localhost:8085/PIDEV/Offer/archive-Offer/2
			@PutMapping("/archive-Offer/{Offer-id}")
			@ResponseBody
			public Offer archiveOffer(@PathVariable("Offer-id") Long OfferId) {
			return offerService.archiverOffer(OfferId);
			}
				
		// http://localhost:8085/PIDEV/Offer/desarchive-Offer/2
			@PutMapping("/desarchive-Offer/{Offer-id}")
			@ResponseBody
			public Offer desarchiveOffer(@PathVariable("Offer-id") Long OfferId) {
			return offerService.desarchiverOffer(OfferId);
			}
			
}
