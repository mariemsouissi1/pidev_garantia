package tn.esprit.infini2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import tn.esprit.infini2.entities.Sinister;
import tn.esprit.infini2.services.ISinisterService;
import tn.esprit.infini2.services.Iindemnity_payService;

public class SinisterController {
	
	@Autowired
	ISinisterService IsinisterService ; 
	
	@Autowired
	Iindemnity_payService paymentService ;
	

	
	// http://localhost:8081/pidev/retrieve-all-sinisters
	 @GetMapping("/retrieve-all-sinisters")
	 @ResponseBody
		public List<Sinister> getSinisters(){
			
			List<Sinister> list = IsinisterService.retrieveAllSinisters();
			return list;
			
			
		}
	
	 
	// http://localhost:8081/pidev/add-sinister
	 @PostMapping("/add-sinister")
	  @ResponseBody
	  public Sinister addSinister(@RequestBody Sinister s  ) { 
	  Sinister sinister = IsinisterService.addSinister(s  );
	  return sinister;
	  }
	 
	 
	//http://localhost:8081/SpringMVC/pidev/declarerSinistre/{id-contract} 
	 @PutMapping("/declarerSinistre/{id-contract}")
	 @ResponseBody
	 public Sinister declarerSinistre(@RequestBody Sinister sinister  , @PathVariable("id-contract") int contractId ) {
	 	
	 	 return  IsinisterService.declarerSinistre(sinister, contractId );
	 }
	 
	 
	//http://localhost:8081/SpringMVC/pidev/affecter-sinister-contract/{id-contract}/{id-sinister}
	 @PutMapping("/affecter-sinister-contract/{id-contract}/{id-sinister}")
	 @ResponseBody
	 public Sinister affecterContratSinister(@PathVariable("id-contract") int idContrat, @PathVariable("id-sinister") int idSinister){
	 	
	 	 return  IsinisterService.affecterContratSinister(idContrat, idSinister) ;
	 	
	 }
	 
	//http://localhost:8081/SpringMVC/pidev/remove-sinister/{sinister-id}
	 @DeleteMapping("/remove-sinister/{sinister-id}")
	 @ResponseBody
	  public String removeSinister(@PathVariable("sinister-id") int id) {
	 	IsinisterService.deleteSinister(id);
	 	return "sinister deleted!" ;
	  }
	//http://localhost:8081/SpringMVC/pidev/retrieve-sinister/{sinister-id}
	 @GetMapping("/retrieve-sinister/{sinister-id}")
	 @ResponseBody
	 public Sinister  getSinistersById(@PathVariable("sinister-id") int id) {
		Sinister s = IsinisterService.retrieveSinister(id);
	 return s ;
	 }

	//http://localhost:8081/SpringMVC/pidev/retrieve-sinister-ByContractId/{contract-id}
	 @GetMapping("/retrieve-sinister-ByContractId/{contract-id}")
	 @ResponseBody
	 public List<Sinister>   getSinistersByContract(@PathVariable("contract-id") int idContract) {   
	 	  List<Sinister> list =IsinisterService.retrieveSinisterByContract( idContract  ) ;
	  return list ;
	 }

	//http://localhost:8081/SpringMVC/servlet/retrieve-sinister-ByInsurerId/{insurer-id}
	 @GetMapping("/retrieve-sinister-ByInsurerId/{insurer-id}")
	 @ResponseBody
	 public List<Sinister>  getSinistersByInsurer(@PathVariable("employee-id") int employeeId) {    
	 	List<Sinister> list = IsinisterService.retrieveSinisterByEmployee( employeeId  ) ;
	 	return list ; 

	 }



}
