package tn.esprit.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.entities.Agent;
import tn.esprit.entities.CustomerAccount;
import tn.esprit.entities.LoanSimulation;
import tn.esprit.repositories.LoanSimulationRepository;
import tn.esprit.services.IBankService;
import tn.esprit.services.ILoanSimulationService;
import tn.esprit.services.IMailService;
import tn.esprit.services.IPdfLoanService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/simulation")
public class LoanSimulationController {

	@Autowired
	ILoanSimulationService iLoanSimulationService;
	
	@Autowired 
	LoanSimulationRepository loanSimulationRepository;
	
	@Autowired
	IBankService iBankService;

	
	@Autowired 
	IPdfLoanService iPdfservice;
	
	@Autowired
	IMailService iMailService;
	
///////////////////////////////Simulate///////////////////////////

	
	// http://localhost:8087/pidevmariem/simulation/simulate/
	//@PreAuthorize("hasAuthority(@userService.Customer())")
	@PostMapping("/simulate/{idOffer}/{nameBank}/{nbrAnnee}/{salaire}")
	@ResponseBody
	public LoanSimulation Simulate(@PathVariable("idOffer") long idOffer,
									@PathVariable("nameBank") String nameBank,
									@PathVariable("nbrAnnee") int nbrAnnee,
									@PathVariable("salaire") double salaire ){
		
		return iLoanSimulationService.simulate(nameBank, nbrAnnee, idOffer, salaire);
	}
	
///////////////////////////////Add simulation with sending mails////////////////////////////
	
	// http://localhost:8087/pidevmariem/simulation/pdf/idLoan	
	
	@PostMapping("/pdf/{idloan}")
	@ResponseBody
	public String pdf(@PathVariable long idloan) {
			iPdfservice.toPDF(idloan);
		 return "pdf generated";
	}
	
	// http://localhost:8087/pidevmariem/simulation/mail/idLoan	
	@PostMapping("/mail/{idloan}")
	@ResponseBody
	public String EmailWithPdf(@PathVariable long idloan) throws MailException, MessagingException {
		Agent agent =iLoanSimulationService.getSimulationById(idloan).getBankLoan().getAgentBank();
		CustomerAccount customer =iLoanSimulationService.getSimulationById(idloan).getCustomerAccountLoan();
		iMailService.sendWithAttachment(customer, agent, iPdfservice.toPDF(idloan));
		 return "Email sent with attachement";
	}
	
	// http://localhost:8087/pidevmariem/simulation/addSimulation/
	//@PreAuthorize("hasAuthority(@userService.Customer())")
	
	@PostMapping("/addSimulation/{idOffer}/{idCustomer}/{nameBank}/{nbrAnnee}/{salaire}")
	@ResponseBody
	public ResponseEntity<String> addSimulation(@PathVariable("idOffer") long idOffer,
										@PathVariable("idCustomer") long idCustomer,
										@PathVariable("nameBank") String nameBank,
										@PathVariable("nbrAnnee")  int nbrAnnee,
										@PathVariable("salaire") double salaire) throws MailException, MessagingException{
		 List<LoanSimulation> list=new ArrayList<LoanSimulation>();
		 list=loanSimulationRepository.countAllSimulationsByidCustomer(idCustomer);
		 
		if(list.isEmpty())
		{		
			iLoanSimulationService.addSimulation(nameBank, nbrAnnee, idOffer, salaire, idCustomer);
			return ResponseEntity.ok("Simulation has been add !");
		}
		else {			
		return ResponseEntity.ok("Sorry you have a simulation already in progress!");
		}
	}
	
///////////////////////////////////////////CONFIRMATION//////////////////////////////
	
	// http://localhost:8087/pidevmariem/simulation/confirmSimulation/
	@PutMapping("/confirmSimulation/{id-loan}")
	//@ResponseBody 
	public void confirmSimulation(@PathVariable("id-loan") long idLoan ) throws MailException, MessagingException{
		iLoanSimulationService.confirmSimulation(idLoan);
		}
	
	// http://localhost:8087/pidevmariem/simulation/unconfirmSimulation/
	@PutMapping("/unconfirmSimulation/{id-loan}")
	@ResponseBody 
	public void unconfirmSimulation(@PathVariable("id-loan") long idLoan ) throws MailException, MessagingException{
		iLoanSimulationService.unConfirmSimulation(idLoan);
		        }
	
	
/////////////////////////////////////Retrieve data from DB///////////////////////////////////////////////////////////

	
	// http://localhost:8087/pidevmariem/simulation/getAllSimulations
	@GetMapping("/getAllSimulations")
    @ResponseBody
	public List<LoanSimulation> getAllSimulations() {	
		return iLoanSimulationService.getAllSimulations();
	}
	
	// http://localhost:8087/pidevmariem/simulation/getAllSimulationsByCin
	@GetMapping("/getAllSimulationsByCin/{cin}")
    @ResponseBody
	public List<LoanSimulation> getAllSimulationsByCin(@PathVariable("cin") long cin ) {	
		return iLoanSimulationService.getAllSimulationsByCin(cin);
	}
	
	// http://localhost:8087/pidevmariem/simulation/getAllSimulationsByNameBank
	@GetMapping("/getAllSimulationsByNameBank/{nameBank}")
    @ResponseBody
	public List<LoanSimulation> getAllSimulationsByNameBank(@PathVariable("nameBank") String nameBank ) {	
		return iLoanSimulationService.getAllSimulationsByNameBank(nameBank);
	}

/////////////////////////DELETE////////////////////////	
	
	// http://localhost:8087/pidevmariem/simulation/deleteSimulationById/
	@DeleteMapping("/deleteSimulationById/{id}")
	@ResponseBody 
	public void deleteSimulationById(@PathVariable("id") long id ){
		iLoanSimulationService.deleteSimulationById(id);
		}
	
	// http://localhost:8087/pidevmariem/simulation/deleteSimulationByIdAgent/
	@DeleteMapping("/deleteOrNotif")
	@ResponseBody 
	public void deleteSimulationBy() throws MailException, MessagingException{
		iLoanSimulationService.deleteOrNotifSimulationInScheduling();
		}
	


	
}



