package tn.esprit.infini2.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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

import tn.esprit.infini2.entities.Provisions;
import tn.esprit.infini2.services.IProvisionsService;

@RestController
@RequestMapping("/provisions")
public class ProvisionsController {
	
	
	@Autowired
	IProvisionsService provisionsService;

	// http://localhost:8089/SpringMVC/provisions/retrieve-all-provisionss
	@GetMapping("/retrieve-all-provisionss")
	@ResponseBody
	public List<Provisions> getProvisionss() {
	List<Provisions> listProvisionss = provisionsService.retrieveAllProvisions();
	return listProvisionss;}
	// http://localhost:8089/SpringMVC/provisions/retrieve-provisions/8
	@GetMapping("/retrieve-provisions/{provisions-id}")
	@ResponseBody
	public Provisions retrieveProvisions(@PathVariable("provisions-id") Long provisionsId) {
	return provisionsService.retrieveProvisions(provisionsId);
	}
	
	// http://localhost:8089/SpringMVC/provisions/remove-provisions/{provisions-id}
	@DeleteMapping("/remove-provisions/{provisions-id}")
	@ResponseBody
	public void removeProvisions(@PathVariable("provisions-id") Long provisionsId) {

	 provisionsService.removeProvisions(provisionsId);
	}

	// http://localhost:8089/SpringMVC/provisions/modify-provisions
	@PutMapping("/modify-provisions")
	@ResponseBody
	public Provisions modifyProvisions(@RequestBody Provisions provisions) {
	return provisionsService.updateProvisions(provisions);
	}

	// http://localhost:8089/SpringMVC/provisions/add-provisions
	@PostMapping("/add-provisions")
	@ResponseBody
	public Provisions addProvisions(@RequestBody Provisions o)
	{
	Provisions provisions = provisionsService.addProvisions(o);
	return provisions;
	}
	// http://localhost:8089/SpringMVC/provisions/CalculateProvisionsConnues
	@GetMapping("/CalculateProvisionsConnues")
	@ResponseBody
	public double CalculateProvisionsConnues() {
	return provisionsService.CalculateProvisionsConnues();
	}
	

	// http://localhost:8089/SpringMVC/provisions/CalculateProvisionsNonConnues
	@GetMapping("/CalculateProvisionsNonConnues")
	@ResponseBody
	public double CalculateProvisionsNonConnues(@RequestParam String BeginigOfYear,@RequestParam String endDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
	return provisionsService.CalculateProvisionsNonConnues(LocalDate.parse(BeginigOfYear,formatter), LocalDate.parse(endDate,formatter));
		
	}
}
