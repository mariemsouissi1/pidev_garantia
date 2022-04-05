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

import tn.esprit.infini2.entities.Sinister;
import tn.esprit.infini2.services.ISinisterService;

@RestController
@RequestMapping("/sinister")
public class SinisterController {
	
	
	@Autowired
	ISinisterService sinisterService;

	// http://localhost:8089/SpringMVC/sinister/retrieve-all-sinisters
	@GetMapping("/retrieve-all-sinisters")
	@ResponseBody
	public List<Sinister> getSinisters() {
	List<Sinister> listSinisters = sinisterService.retrieveAllSinisters();
	return listSinisters;}
	// http://localhost:8089/SpringMVC/sinister/retrieve-sinister/8
	@GetMapping("/retrieve-sinister/{sinister-id}")
	@ResponseBody
	public Sinister retrieveSinister(@PathVariable("sinister-id") Long sinisterId) {
	return sinisterService.retrieveSinister(sinisterId);
	}
	
	// http://localhost:8089/SpringMVC/sinister/remove-sinister/{sinister-id}
	@DeleteMapping("/remove-sinister/{sinister-id}")
	@ResponseBody
	public void removeSinister(@PathVariable("sinister-id") Long sinisterId) {

	 sinisterService.retrieveSinister(sinisterId);
	}

	// http://localhost:8089/SpringMVC/sinister/modify-sinister
	@PutMapping("/modify-sinister")
	@ResponseBody
	public Sinister modifySinister(@RequestBody Sinister sinister) {
	return sinisterService.updateSinister(sinister);
	}

	// http://localhost:8089/SpringMVC/sinister/add-sinister
	@PostMapping("/add-sinister")
	@ResponseBody
	public Sinister addSinister(@RequestBody Sinister o)
	{
	Sinister sinister = sinisterService.addSinister(o);
	return sinister;
	}
	

}
