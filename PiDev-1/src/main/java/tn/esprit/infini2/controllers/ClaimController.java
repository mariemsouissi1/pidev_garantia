package tn.esprit.infini2.controllers;

import java.util.List;
import java.util.Map;

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
		emailservice.sendEmail(s.getClaim_email(),"Confirmation","Bonjour Monsieur/Madame,\r\n"
				+ "\r\n"
				+ "Nous avons bien reçu votre demande concernant votre reclamation. Nous sommes sincèrement désolés pour ce désagrément. Nous mettons tout en œuvre pour résoudre ce problème au plus vite. Merci d’avance de votre patience.\r\n"
				+ "\r\n"
				+ "En attendant, permettez-moi de partager avec vous les ressources liées au problème. Vous y trouverez peut-être des réponses à votre question.\r\n"
				+ "\r\n"
				+ "Merci de votre patience,\r\n"
				+ "\r\n"
				+ "Cordialement,\r\n"
				+ "\r\n"
				+ "Oumayma Bichiou");
		return Claim;
		}
///////////////////////////////////////////update claim/////////////////////////////////////////
		
	// http://localhost:8085/PIDEV/claim/modify-Claim
		@PutMapping("/modify-Claim")
		@ResponseBody
		public Claim modifyClaim(@RequestBody Claim claim) {
		Claim c=ClaimService.retrieveClaim(claim.getClaim_id());
		claim.setClaim_state(c.getClaim_state());
		claim.setClaim_type(c.getClaim_type());
		claim.setClaim_visibility(c.getClaim_visibility());
		return ClaimService.updateClaim(claim);
		}
/////////////////////////////////////////archive claim//////////////////////////////////////////
		
	// http://localhost:8085/PIDEV/claim/archive-Claim/2
		@PutMapping("/archive-Claim/{Claim-id}")
		@ResponseBody
		public Claim archiveClaim(@PathVariable("Claim-id") Long ClaimId) {
		return ClaimService.archiverClaim(ClaimId);
		}

/////////////////////////////////////////desarchive claim//////////////////////////////////////////

		
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
		
	// http://localhost:8085/PIDEV/claim/retrieve-claim-par-client/2
		@GetMapping("/retrieve-claim-par-client/{Claim-id}")
		@ResponseBody
		public List<Claim> getClaims_client(@PathVariable("client-id") Long idClient) {
		List<Claim> listClaims = ClaimService.retrieveclaim_par_client(idClient);
		return listClaims;
		}
		
		
//////////////////////////////////////delete claim//////////////////////////////////////		

	// http://localhost:8085/PIDEV/claim/remove-Claim/2
		@DeleteMapping("/remove-Claim/{Claim-id}")
		@ResponseBody
		public String removeClaim(@PathVariable("Claim-id") Long ClaimId) {
			ClaimService.deleteClaim(ClaimId);
			return("Claim deleted !!!!! ");
		}
		
///////////////////////////////////////treat claim//////////////////////////////////////

	// http://localhost:8085/PIDEV/claim/Treat_claim/2
		@PutMapping("/Treat_claim/{Claim-id}")
		@ResponseBody
		public Claim Treat_claim(@PathVariable("Claim-id") Long ClaimId,@RequestParam String body) {
		return ClaimService.treated_claim(ClaimId,body);
		 
		}
		
///////////////////////////////////////ongoing claim//////////////////////////////////////

	// http://localhost:8085/PIDEV/claim/ongoing_claim/2
	@PutMapping("/ongoing_claim/{Claim-id}")
	@ResponseBody
	public Claim ongoing_claim(@PathVariable("Claim-id") Long ClaimId) {
	return ClaimService.ongoing_claim(ClaimId);

}
////////////////////////////////////////STATISTICS//////////////////////////////////////////
	
	                       ////////////type contrat/////////
	
// http://localhost:8085/PIDEV/claim/retrieve-statistics-contrat
	@GetMapping("/retrieve-statistics-contrat")
	@ResponseBody
	public Map<String, Double> get_stais_contrat_claim() {
	Map<String, Double> MAPClaims = ClaimService.stat_claim_contrat();
	Map<String, Double> MAPClaims_pour = ClaimService.statis_map_pour(MAPClaims);
	return MAPClaims_pour;
	}
	
                            //////////// state claim /////////
	
// http://localhost:8085/PIDEV/claim/retrieve-statistics-state
	@GetMapping("/retrieve-statistics-state")
	@ResponseBody
	public Map<String, Double> get_stais_state_claim() {
	Map<String, Double> MAPClaims = ClaimService.statis_par_state();
	Map<String, Double> MAPClaims_pour = ClaimService.statis_map_pour(MAPClaims);
	return MAPClaims_pour;
	}
                             //////////// type claim /////////
	
//http://localhost:8085/PIDEV/claim/retrieve-statistics-typeclaim
	@GetMapping("/retrieve-statistics-typeclaim")
	@ResponseBody
	public Map<String, Double> get_stais_type_claim() {
	Map<String, Double> MAPClaims = ClaimService.statis_par_type();
	Map<String, Double> MAPClaims_pour = ClaimService.statis_map_pour(MAPClaims);
	return MAPClaims_pour;
	

	}
}
