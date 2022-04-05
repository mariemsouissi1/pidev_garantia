package tn.esprit.infini2.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.infini2.entities.Offer;
import tn.esprit.infini2.services.IOfferService;

@RestController
@RequestMapping("/offer")
public class OfferController {
	@Autowired
	IOfferService offerService;
	
////////////////////////////////////////////uploaaaaaaaaaaaaad 11111111111///////////////////////////
	// http://localhost:8085/PIDEV/offer/upload
	@RequestMapping(value="/upload", method=RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Object> uploadFile(@RequestParam("offer_image") MultipartFile file) throws IOException {
		File convertFile = new File("C:\\Users\\ASUS\\Desktop\\dossiers\\4INFINI\\SPRING\\PidevGarantiaa\\src\\main\\resources\\"+file.getOriginalFilename());
		convertFile.createNewFile();
		FileOutputStream fout = new FileOutputStream(convertFile);
		fout.write(file.getBytes());
		fout.close();
		return new ResponseEntity<>("File is uploaded successfully", HttpStatus.OK);
	}
////////////////////////////////////////////uploaaaaaaaaaaaaad 11111111111///////////////////////////
// http://localhost:8085/PIDEV/offer/upload/1
@RequestMapping(value="/upload/1", method=RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public ResponseEntity<Object> uploadFileandsave(@RequestParam("offer_image") MultipartFile file,
		@RequestParam("offer_id")Long id) throws IOException {
File convertFile = new File("C:\\Users\\ASUS\\Desktop\\dossiers\\4INFINI\\SPRING\\PidevGarantiaa\\src\\main\\resources\\templates\\"+file.getOriginalFilename());
convertFile.createNewFile();
Offer o=offerService.retrieveOffer(id);
//o.setOffer_image(convertFile);
FileOutputStream fout = new FileOutputStream(convertFile);
fout.write(file.getBytes());
fout.close();
return new ResponseEntity<>("File is uploaded successfully", HttpStatus.OK);
}
////////////////////////////////////////////////retrieve ///////////////////////////////////////////
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
//////////////////////////////////////////add offer//////////////////////////////////////
	// http://localhost:8085/PIDEV/offer/add-Offer
	@PostMapping("/add-Offer")
	@ResponseBody
	public Offer addOffer(@RequestBody Offer o)
	{
	Offer offer = offerService.addOffer(o);
	
	return offer;
	}
//////////////////////////////////////////remove Offer//////////////////////////////////////

	// http://localhost:8085/PIDEV/offer/remove-Offer/2
	@DeleteMapping("/remove-Offer/{Offer-id}")
	@ResponseBody
	public String removeOffer(@PathVariable("Offer-id") Long OfferId) {
		offerService.deleteOffer(OfferId);
		return ("Offer deleted !!!!!");
		
	}
//////////////////////////////////////////update Offer//////////////////////////////////////

	// http://localhost:8085/PIDEV/offer/modify-Offer
	@PutMapping("/modify-Offer")
	@ResponseBody
	public Offer modifyOffer(@RequestBody Offer offer) {
	Long id=offerService.return_Offer_id(offer);
	Offer o =offerService.retrieveOffer(id);
	offer.setOffer_categorie(o.getOffer_categorie());
	offer.setOffer_visibility(o.getOffer_visibility());
	return offerService.updateOffer(offer);
	}

//////////////////////////////////////archive-Offer/////////////////////////////////////////
	
	// http://localhost:8085/PIDEV/Offer/archive-Offer/2
			@PutMapping("/archive-Offer/{Offer-id}")
			@ResponseBody
			public Offer archiveOffer(@PathVariable("Offer-id") Long OfferId) {
			return offerService.archiverOffer(OfferId);
			}
			
//////////////////////////////////////desarchive-Offer/////////////////////////////////////
			
	// http://localhost:8085/PIDEV/Offer/desarchive-Offer/2
			@PutMapping("/desarchive-Offer/{Offer-id}")
			@ResponseBody
			public Offer desarchiveOffer(@PathVariable("Offer-id") Long OfferId) {
			return offerService.desarchiverOffer(OfferId);
			}
////////////////////////////////////calculer age////////////////////////////////////////////
			// http://localhost:8085/PIDEV/offer/calculer_age
			@GetMapping("/calculer_age")
			@ResponseBody
			public Long calculer_age(@RequestParam Date date) {
			return offerService.calculer_age(date);
			}
}
