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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.infini2.entities.Claim;
import tn.esprit.infini2.services.IClaimService;
import tn.esprit.infini2.services.Iemailservice;

@RestController
@RequestMapping("/claim")
public class ClaimController {
	
	
	@Autowired
	IClaimService ClaimService;
	@Autowired
	Iemailservice emailservice;
	
	
////////////////////////////////////////////add claim/////////////////////////////////////////	
	
	// http://localhost:8085/PIDEV/claim/add-Claim
		@PostMapping("/add-Claim")
		@ResponseBody
		public Claim addClaim(@RequestBody Claim s)
		{
		Claim Claim = ClaimService.addClaim(s);
		emailservice.sendEmail(s.getClaim_email(),"Confirmation","nous avons bien reçu votre reclamation. nous vous répondrons au plus vite. merci de votre patience");
		return Claim;
		}
///////////////////////////////////////////update claim/////////////////////////////////////////
		
	// http://localhost:8085/PIDEV/claim/modify-Claim
		@PutMapping("/modify-Claim")
		@ResponseBody
		public Claim modifyClaim(@RequestBody Claim Claim) {
		return ClaimService.updateClaim(Claim);
		}
		
	// http://localhost:8085/PIDEV/claim/archive-Claim/2
		@PutMapping("/archive-Claim/{Claim-id}")
		@ResponseBody
		public Claim archiveClaim(@PathVariable("Claim-id") Long ClaimId) {
		return ClaimService.archiverClaim(ClaimId);
		}
			
	// http://localhost:8085/PIDEV/claim/desarchive-Claim/2
		@PutMapping("/desarchive-Claim/{Claim-id}")
		@ResponseBody
		public Claim desarchiveClaim(@PathVariable("Claim-id") Long ClaimId) {
		return ClaimService.desarchiverClaim(ClaimId);
		}
		
/////////////////////////////////////////retrieve claim///////////////////////////////////////////		
			
	// http://localhost:8085/PIDEV/claim/retrieve-all-Claims
		@GetMapping("/retrieve-all-Claims")
		@ResponseBody
		public List<Claim> getClaims() {
		List<Claim> listClaims = ClaimService.retrieveAllClaims();
		return listClaims;
		}
	
	// http://localhost:8085/PIDEV/claim/retrieve-Claim/2
		@GetMapping("/retrieve-Claim/{Claim-id}")
		@ResponseBody
		public Claim retrieveClaim(@PathVariable("Claim-id") Long idClaim) {
		return ClaimService.retrieveClaim(idClaim);
		}
		
	// http://localhost:8085/PIDEV/claim/retrieve-visible
		@GetMapping("/retrieve-visible")
		@ResponseBody
		public List<Claim> getClaimsvisible() {
		List<Claim> listClaims = ClaimService.retrievevisible();
		return listClaims;
		}
	
	// http://localhost:8085/PIDEV/claim/retrieve-non-visible
		@GetMapping("/retrieve-non-visible")
		@ResponseBody
		public List<Claim> getClaimsnonvisible() {
		List<Claim> listClaims = ClaimService.retrievenonvisible();
		return listClaims;
		}
		
		
//////////////////////////////////////delete claim//////////////////////////////////////		

	// http://localhost:8085/PIDEV/claim/remove-Claim/2
		@DeleteMapping("/remove-Claim/{Claim-id}")
		@ResponseBody
		public void removeClaim(@PathVariable("Claim-id") Long ClaimId) {
			ClaimService.deleteClaim(ClaimId);
		}
///////////////////////////////////////treat claim//////////////////////////////////////

	// http://localhost:8085/PIDEV/claim/Treat_claim/2
		@PutMapping("/Treat_claim/{Claim-id}")
		@ResponseBody
		public Claim Treat_claim(@PathVariable("Claim-id") Long ClaimId,@RequestParam String body) {
		return ClaimService.treated_claim(ClaimId,body);
		 
		}
///////////////////////////////////////treat claim//////////////////////////////////////

	// http://localhost:8085/PIDEV/claim/ongoing_claim/2
	@PutMapping("/ongoing_claim/{Claim-id}")
	@ResponseBody
	public Claim ongoing_claim(@PathVariable("Claim-id") Long ClaimId) {
	return ClaimService.ongoing_claim(ClaimId);

}
}
