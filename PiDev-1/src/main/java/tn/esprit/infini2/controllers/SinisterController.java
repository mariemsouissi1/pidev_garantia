package tn.esprit.infini2.controllers;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/Sinister")
public class SinisterController {
	
	@Autowired
	ISinisterService IsinisterService;
	
   
				 
	//http://localhost:8083/pidev/sinister/verificationSinisterDelay/{sinister-id}
	
	@GetMapping("verificationSinisterDelay/{sinister-id}")
	@ResponseBody
	public String  verificationSinisterDelay(@PathVariable("sinister-id") Integer idSinister) {
		
	return IsinisterService.verificationSinisterDelay(idSinister) ;
    }
	
	
// http://localhost:8083/pidev/Sinister/traiterSinistre/{sinister-id}
	@GetMapping("/traitertSinistre/{sinister-id}")
	@ResponseBody
	public String  traiterSinister(@PathVariable("sinister-id") Integer idSinister  ) throws Exception {
		
	return IsinisterService.traiterSinister(idSinister);
    }
	
	
	
		// http://localhost:8083/pidev/Sinister/retrieve-all-sinisters
	 @GetMapping("/retrieve-all-sinisters")
	 @ResponseBody
		public List<Sinister> getSinisters(){
			
			List<Sinister> list = IsinisterService.retrieveAllSinisters();
			return list;
		}
	 
	 
	// http://localhost:8083/pidev/Sinister/update-Sinister/{sinister-id}
	 @PutMapping("/update-Sinister/{sinister-id}")
	 @ResponseBody
	  public String updateSinister(@RequestBody Sinister sinister ,@PathVariable("sinister-id") int idSinister) {
	  
	 		 
	 		 
	 		 return IsinisterService.updateSinister(sinister ,  idSinister);
	  }
	 
	 
	 
	 
	 
	// http://localhost:8083/pidev/Sinister/add-sinister
	 @PostMapping("/add-sinister")
	  @ResponseBody
	  public Sinister addSinister(@RequestBody Sinister s  ) { 
	  Sinister sinister = IsinisterService.addSinister(s  );
	  return sinister;
	  }
	 
	 
	 
	// http://localhost:8083/pidev/Sinister/retrieve-sinister-ByEmployeeId/{employee-id}
	 @PreAuthorize("hasAuthority(@userService.Employee())")
	@GetMapping("/retrieve-sinister-ByEmployeeId/{employee-id}")
	@ResponseBody
	public List<Sinister>  getSinistersByEmployee(@PathVariable("employee-id") Long idEmployee) {    
		List<Sinister> list = IsinisterService.retrieveSinisterByEmployee(  idEmployee  ) ;
		
		return list ; 

	}
	
	
	// http://localhost:8083/pidev/Sinister/retrieve-sinister-ByCustomerId/{customer-id}
	@PreAuthorize("hasAuthority(@userService.Customer())")
	@GetMapping("/retrieve-sinister-ByCustomerId/{customer-id}")
	@ResponseBody
	public List<Sinister>  getSinistersByCustomer(@PathVariable("customer-id") Long idCustomer) {    
		List<Sinister> list = IsinisterService.retrieveSinisterByCustomer(  idCustomer  ) ;
		
		return list ; 

	}
	
	// http://localhost:8083/pidev/Sinister/retrieve-sinister/{sinister-id}
	@GetMapping("/retrieve-sinister/{sinister-id}")
	@ResponseBody
    public Sinister  getSinistersById(@PathVariable("sinister-id") Integer id) {
	Sinister s = IsinisterService.retrieveSinister(id);
    return s ;
	}
	

	//http://localhost:8083/pidev/Sinister/delete-Sinister/{sinister-id}
	@DeleteMapping("/delete-Sinister/{sinister-id}")
	@ResponseBody
	 public String deleteSinister(@PathVariable("sinister-id") Integer id) {
		IsinisterService.deleteSinister(id);
		return "sinister deleted!" ;
	 }
	
	
	
	 
	//http://localhost:8083/pidev/Sinister/suivreSinistre/{sinister-id}
	@GetMapping("/suivreSinistre/{sinister-id}")
	@ResponseBody
	public String  suivreSinister(@PathVariable("sinister-id") Integer idSinister) {
		
	return IsinisterService.suivreSinistre(idSinister) ;
	}
	
	
	

	//http://localhost:8083/pidev/Sinister/calcul-Rapidité/{sinister-id}
	@GetMapping("/calcul-Rapidité/{sinister-id}")
	@ResponseBody
	public String  ratioRapidité(@PathVariable("sinister-id") Integer idSinister) {
		
	return IsinisterService.CalculRapiditéReglementSinistre(idSinister) ;
	}
	
	//http://localhost:8083/pidev/Sinister/retrieve-sinister-ByDeclarationDate/{from}/{to}
	@GetMapping("/retrieve-sinister-ByDeclarationDate/{from}/{to}")
	@ResponseBody
	public List<Sinister>  findByDeclarationDateBetween(@PathVariable("from")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date from,@PathVariable("to")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date to) {    
	List<Sinister> list = IsinisterService.retrieveSinisterByDeclarationDate( from , to ) ;
	return list;
}
	//http://localhost:8083/pidev/Sinister/retrieve-sinister-BySinisterDate/{from}/{to}
	@GetMapping("/retrieve-sinister-BySinisterDate/{from}/{to}")
	@ResponseBody
	public List<Sinister>  retrieveSinisterBySinisterDate(@PathVariable("from")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date from,@PathVariable("to") 
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date to) {    
	List<Sinister> list = IsinisterService.retrieveSinisterBySinisterDate( from , to ) ;
	return list ; 
    }

}
