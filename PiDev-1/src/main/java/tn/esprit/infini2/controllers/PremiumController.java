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

import tn.esprit.infini2.entities.Contract;
import tn.esprit.infini2.entities.Premium;
import tn.esprit.infini2.services.IPremiumService;

@RestController
@RequestMapping("/premium")
public class PremiumController {
	
	

	@Autowired
	IPremiumService premiumService;

	// http://localhost:8089/SpringMVC/premium/retrieve-all-premiums
	@GetMapping("/retrieve-all-premiums")
	@ResponseBody
	public List<Premium> getPremiums() {
	List<Premium> listPremiums = premiumService.retrieveAllPremiums();
	return listPremiums;}
	// http://localhost:8089/SpringMVC/premium/retrieve-premium/8
	@GetMapping("/retrieve-premium/{premium-id}")
	@ResponseBody
	public Premium retrievePremium(@PathVariable("premium-id") Long premiumId) {
	return premiumService.retrievePremium(premiumId);
	}
	
	// http://localhost:8089/SpringMVC/premium/remove-premium/{premium-id}
	@DeleteMapping("/remove-premium/{premium-id}")
	@ResponseBody
	public void removePremium(@PathVariable("premium-id") Long premiumId) {

	 premiumService.removePremium(premiumId);
	}

	// http://localhost:8089/SpringMVC/premium/modify-premium
	@PutMapping("/modify-premium")
	@ResponseBody
	public Premium modifyPremium(@RequestBody Premium premium) {
	return premiumService.updatePremium(premium);
	}

	// http://localhost:8089/SpringMVC/premium/add-premium
	@PostMapping("/add-premium")
	@ResponseBody
	public Premium addPremium(@RequestBody Premium o)
	{
	Premium premium = premiumService.addPremium(o);
	return premium;
	}
	// http://localhost:8089/SpringMVC/premium/calculatePremium
		@GetMapping("/calculatePremium")
		@ResponseBody
	public Double calculatePremium(@RequestParam double PPValue , @RequestParam String typeA ,@RequestParam long salary ) {
		Double Pr = premiumService.calculatePremium(PPValue,typeA,salary);
		return Pr;
	}
	

}
