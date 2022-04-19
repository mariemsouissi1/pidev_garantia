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

import tn.esprit.infini2.entities.Bank;
import tn.esprit.infini2.services.IBankService;



@RestController
@RequestMapping("/bank")
public class BankController {

	@Autowired
	IBankService ibankService;
	

/////////////////////////ADD/////////////////////
	
	// http://localhost:8087/pidevmariem/bank/addBank
	@PostMapping("/addBank")
	@ResponseBody
	public Bank addBank(@RequestBody Bank bank){
		List<Bank> banks= ibankService.getAllBanks();
		
		ibankService.addBank(bank);
		return bank;
	}
/////////////////////////UPDATE WITH CONTROL/////////////////////

	// http://localhost:8087/pidevmariem/bank/updateBank/idBank
	@PutMapping("/updateBank/{idBank}")
	@ResponseBody 
	public Bank updateBank(@RequestBody Bank bank, @PathVariable("idBank") long idBank){
		return ibankService.updateBank(idBank,bank);

	}	

/////////////////////////GET/////////////////////

	// http://localhost:8087/pidevmariem/bank/getAllBanks
	@GetMapping("/getAllBanks")
    @ResponseBody
	public List<Bank> getAllBanks() {
		
		return ibankService.getAllBanks();
	}
	
	// http://localhost:8087/pidevmariem/bank/getBankById/idBank
	@GetMapping("/getBankById/{idBank}")
	@ResponseBody
	public Bank getBankById(@PathVariable("idBank") long idBank){
		return ibankService.getBankById(idBank);
	}
	
	// http://localhost:8087/pidevmariem/bank/getBankByName/NameBank
	@GetMapping("/getBankByName/{NameBank}")
	@ResponseBody
	public Bank getBankByName(@PathVariable("NameBank") String NameBank){
		return ibankService.getBankByName(NameBank);
	}
	
/////////////////////////DELETE/////////////////////

	// http://localhost:8087/pidevmariem/bank/deleteBank/idBank
	@DeleteMapping("/deleteBank/{idBank}")
	@ResponseBody 
	public void deleteBankById(@PathVariable("idBank") long idBank ){
		ibankService.deleteBankById(idBank);
	}
	
}
