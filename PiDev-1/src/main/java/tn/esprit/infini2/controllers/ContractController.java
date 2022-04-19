package tn.esprit.infini2.controllers;

import java.io.IOException;

import java.util.Date;
import java.util.HashMap;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import tn.esprit.infini2.entities.Type_Contract;
import tn.esprit.infini2.entities.customer;
import tn.esprit.infini2.services.IContractService;

@RestController
@RequestMapping("/contract")
public class ContractController {
	
	

	@Autowired
	IContractService contractService;

	// http://localhost:8089/SpringMVC/contract/retrieve-all-contracts
	@PreAuthorize("hasAuthority(@userService.Employee())")
	@GetMapping("/retrieve-all-contracts")
	@ResponseBody
	public List<Contract> getContracts() {
	List<Contract> listContracts = contractService.retrieveAllContracts();
	return listContracts;}
	// http://localhost:8089/SpringMVC/contract/retrieve-contract/8
	@PreAuthorize("hasAuthority(@userService.Employee())")
	@GetMapping("/retrieve-contract/{contract-id}")
	@ResponseBody
	public Contract retrieveContract(@PathVariable("contract-id") Long contractId) {
	return contractService.retrieveContract(contractId);
	}
	
	// http://localhost:8089/SpringMVC/contract/remove-contract/{contract-id}
	@DeleteMapping("/remove-contract/{contract-id}")
	@ResponseBody
	public void removeContract(@PathVariable("contract-id") Long contractId) {

	 contractService.removeContract(contractId);
	}

	// http://localhost:8089/SpringMVC/contract/modify-contract
	@PreAuthorize("hasAuthority(@userService.Employee())")
	@PutMapping("/modify-contract")
	@ResponseBody
	public Contract modifyContract(@RequestBody Contract contract) {
	return contractService.updateContract(contract);
	}

	// http://localhost:8089/SpringMVC/contract/add-contract
	@PreAuthorize("hasAuthority(@userService.Employee())")
	@PostMapping("/add-contract")
	@ResponseBody
	public Contract addContract(@RequestBody Contract o)
	{
	Contract contract = contractService.addContract(o);
	return contract;
	}
	// http://localhost:8089/SpringMVC/contract/add-contractplus
	@PreAuthorize("hasAuthority(@userService.Employee())")
		@PostMapping("/add-contractplus/{id}")
		@ResponseBody
	public Contract addContract(@RequestBody Contract c, @PathVariable("id") Long idPremium)
	{
		Contract contract = contractService.addContract(c,idPremium);
		return contract;
		}
	//http://localhost:8089/SpringMVC/contract/countcontractbytype
	@PreAuthorize("hasAuthority(@userService.Employee())")
			@GetMapping("/countcontractbytype/{type}")	
			@ResponseBody
			public int countcontractbytype(@PathVariable("type") String type) {
				
				System.out.println(Type_Contract.valueOf(type));
				
				int countContractByType= contractService.countContractByType(Type_Contract.valueOf(type));
				return countContractByType;
			}
			
			
			//http://localhost:8089/SpringMVC/contract/findcontractbytype
	@PreAuthorize("hasAuthority(@userService.Employee())")
			@PreAuthorize("hasAuthority(@userService.Employee())")
			@GetMapping("/findcontractbytype/{type}")	
			@ResponseBody
			public List<Contract> findContractByType(@PathVariable("type") String type) {
				
				System.out.println(Type_Contract.valueOf(type));
				
				List<Contract> listContract = contractService.findContractByType(Type_Contract.valueOf(type));
				return listContract;
			}
			
			//http://localhost:8089/SpringMVC/contract/pdf/generate
			 @GetMapping("/pdf/generate/{contractID}")
			 @ResponseBody
			    public void export(HttpServletResponse response, @PathVariable("contractID") Long contractID) throws IOException {
			        response.setContentType("application/pdf");
			        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
			        String currentDateTime = dateFormatter.format(new Date());

			        String headerKey = "Content-Disposition";
			        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
			        response.setHeader(headerKey, headerValue);
			       
			        Contract contract = contractService.findContractByID(contractID);
			        customer  _customer = contract.getC_customerAccount().getCustomer();
			        HashMap<String, Object> contractInfo = new HashMap<String, Object>();
			        contractInfo.put("contract", contract);
			        contractInfo.put("customer", _customer);
			        this.contractService.export(response, contractInfo );
			    }
				//http://localhost:8089/SpringMVC/contract/CountContractsBetween
			 @PreAuthorize("hasAuthority(@userService.Employee())")
				@GetMapping("/CountContractsBetween")	
				@ResponseBody
				public int CountContractsBetween(@RequestParam String EndDate, @RequestParam String BiginingDate) {
					
					
					
					int countContractsBetween= contractService.CountContractsBetween(EndDate,BiginingDate);
					return countContractsBetween;
				}
				 
				//http://localhost:8089/SpringMVC/contract/viewContractsByTypes
			 @PreAuthorize("hasAuthority(@userService.Employee())")
				@GetMapping("/viewContractsByTypes")	
				@ResponseBody
				public HashMap<Type_Contract, Object> viewContractsByTypes() {
					
					HashMap<Type_Contract, Object> viewContractsByTypes= contractService.viewContractsByTypes();
					return viewContractsByTypes;
				}
			
}
