package tn.esprit.infini2.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.infini2.entities.Bank;
import tn.esprit.infini2.services.IBankService;




@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/bank")
public class BankController {
	
	@Autowired
	IBankService ibankService;
	

/////////////////////////ADD/////////////////////
	
	@PreAuthorize("hasAuthority(@userService.Employee())")
	// http://localhost:8087/PIDEV_GARANTIA/bank/addBank
	@PostMapping("/addBank")
	@ResponseBody
	public String addBank(@RequestBody Bank bank){
			return ibankService.addBank(bank, bank.getNameBank());

	}
/////////////////////////UPDATE WITH CONTROL/////////////////////

	@PreAuthorize("hasAuthority(@userService.Employee())")
	// http://localhost:8087/PIDEV_GARANTIA/bank/updateBank/idBank
	@PutMapping("/updateBank/{idBank}")
	@ResponseBody 
	public Bank updateBank(@RequestBody Bank bank, @PathVariable("idBank") long idBank){
		return ibankService.updateBank(idBank,bank);

	}	

/////////////////////////GET/////////////////////

	@PreAuthorize("hasAuthority(@userService.Employee(),@userService.Customer())")
	// http://localhost:8087/PIDEV_GARANTIA/bank/getAllBanks
	@GetMapping("/getAllBanks")
    @ResponseBody
	public List<Bank> getAllBanks() {
		
		return ibankService.getAllBanks();
	}
	
	@PreAuthorize("hasAuthority(@userService.Employee())")
	// http://localhost:8087/PIDEV_GARANTIA/bank/getBankById/idBank
	@GetMapping("/getBankById/{idBank}")
	@ResponseBody
	public Bank getBankById(@PathVariable("idBank") long idBank){
		return ibankService.getBankById(idBank);
	}
	
	@PreAuthorize("hasAuthority(@userService.Employee())")
	// http://localhost:8087/PIDEV_GARANTIA/bank/getBankByName/NameBank
	@GetMapping("/getBankByName/{NameBank}")
	@ResponseBody
	public Bank getBankByName(@PathVariable("NameBank") String NameBank){
		return ibankService.getBankByName(NameBank);
	}
	
/////////////////////////DELETE/////////////////////

	@PreAuthorize("hasAuthority(@userService.Employee())")
	// http://localhost:8087/PIDEV_GARANTIA/bank/deleteBank/idBank
	@DeleteMapping("/deleteBank/{idBank}")
	@ResponseBody 
	public void deleteBankById(@PathVariable("idBank") long idBank ){
		ibankService.deleteBankById(idBank);
	}
	
}
