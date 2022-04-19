package tn.esprit.infini2.controllers;



import java.util.List;
import java.util.Map;

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

import tn.esprit.infini2.entities.Credit;
import tn.esprit.infini2.entities.TransactionCredit;
import tn.esprit.infini2.exceptions.InvalidAccountException;
import tn.esprit.infini2.exceptions.InvalidAmountException;
import tn.esprit.infini2.exceptions.InvalidBalanceException;
import tn.esprit.infini2.repositories.CreditRepository;
import tn.esprit.infini2.services.ITransactionCreditService;




@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/transaction")
public class TransactionCreditController {

	@Autowired
	ITransactionCreditService transactionCreditService;

	@Autowired
	CreditRepository CreditRepo;
////////////////////ADD///////////////////:

	
	// http://localhost:8087/PIDEV_GARANTIA/transaction/add-transaction/id
	@PreAuthorize("hasAuthority(@userService.Customer())")
	@PostMapping("/add-transaction/{idCredit}")
	@ResponseBody
	public TransactionCredit passerUnVirement(@RequestBody TransactionCredit transaction,@PathVariable("idCredit") Long idCredit) throws InvalidAccountException, InvalidAmountException, InvalidBalanceException{
    	Credit c= CreditRepo.findById(idCredit).get();
		transaction.setCreditTransaction(c);
		Long emetteur = transaction.getSourceAccountNumber();
    	Long recepteur = transaction.getDestinedAccountNumber();
    	transactionCreditService.createNewVirement(transaction, emetteur, recepteur, idCredit);
    	return transaction;
            }
	
/////////////////////UPDATE WITH CONTROL///////////////////////////////////////////
	
	// http://localhost:8087/PIDEV_GARANTIA/transaction/update-transaction/idTransaction
	@PreAuthorize("hasAuthority(@userService.Customer())")
	@PutMapping("/update-transaction/{idTransaction}")
	@ResponseBody
	public TransactionCredit refreshTransaction(@RequestBody TransactionCredit t ,@PathVariable("idTransaction") long idTransaction)
	{
		return transactionCreditService.updateTransaction(t,idTransaction);
	}



////////////////////////GET ALL///////////////////////////

	// http://localhost:8087/PIDEV_GARANTIA/transaction/retrieve-all-transactions/
	@PreAuthorize("hasAuthority(@userService.Employee(),@userService.Customer())")
	@GetMapping("/retrieve-all-transactions")
	@ResponseBody
	public List<TransactionCredit> retrieveAllTransactions(){
		return transactionCreditService.retrieveAllTransactions();
		}
	
//////////////GET ALL SORTED////////////////////////////
	
	// http://localhost:8087/PIDEV_GARANTIA/transaction/retrieve-all-transactions-sorted/
	
	@PreAuthorize("hasAuthority(@userService.Employee(),@userService.Customer())")
		@GetMapping("/retrieve-all-transactions-sorted")
		@ResponseBody
		public List<TransactionCredit> retrieveAllTransactionsSorted (){
			return transactionCreditService.listAllTransactions();
			}
	
//////////////////GET BY ID///////////////////////////////////

	// http://localhost:8087/PIDEV_GARANTIA/transaction/retrieve-transaction/3

	@PreAuthorize("hasAuthority(@userService.Employee(),@userService.Customer())")
	@GetMapping("/retrieve-transaction/{id-transaction}")
	@ResponseBody
	public TransactionCredit retrieveTransactionById(@PathVariable("id-transaction") long idTransaction){
		return transactionCreditService.retrieveTransaction(idTransaction);
	}

	
	
///////////////////LIST TRANSACTION PER CUSTOMER///////////////////

		// http://localhost:8087/PIDEV_GARANTIA/transaction/listperCustomer/3
		
		@PreAuthorize("hasAuthority(@userService.Employee())")

		@GetMapping("/listperCustomer/{idCustomer}")
		@ResponseBody
		public List<TransactionCredit> retrieveTransactionsByCustomer(@PathVariable("idCustomer") long idCustomer){
				return transactionCreditService.listTransactionByCustomerAccountId(idCustomer);
			}
	

////////////////LIST TRANSACTION PER YEARRRRRRR/////////////////////////////////////////////////

	// http://localhost:8087/PIDEV_GARANTIA/transaction/list-transactions-per-year/2021
		@PreAuthorize("hasAuthority(@userService.Employee())")
		@GetMapping("/list-transactions-per-year/{year}")
		@ResponseBody
		public List<TransactionCredit> listTransactionsPerYear(@PathVariable("year") double year){
				return transactionCreditService.listTransactionByYear(year);
		}
	
/////////////////DICT (Month : AmountTransaction) //////////////////////

		// http://localhost:8087/PIDEV_GARANTIA/transaction/dict/2021
		@PreAuthorize("hasAuthority(@userService.Employee())")
		@GetMapping("/dict/{year}")
		@ResponseBody
		public Map<Double, Double> getStatisticMonthByAmount(@PathVariable("year") double year) {
			Map<Double, Double> map = transactionCreditService.StatisticMonthByAmount(year);
			return map;
		}
	
/////////////////////////////SUM TRANSACTION/////////////////////////////////////////////////////

		// http://localhost:8087/PIDEV_GARANTIA/transaction/somme/
		@PreAuthorize("hasAuthority(@userService.Employee())")
		@GetMapping("/somme")
		@ResponseBody
		public double SommeTransactions() {
			return transactionCreditService.SumTransAmount();
		}	
	
	
//////////////////////////DELETE TRANSACTION////////////////////////////////////

		// http://localhost:8087/PIDEV_GARANTIA/transaction/supp-transaction/3
		@PreAuthorize("hasAuthority(@userService.Customer())")
		@DeleteMapping("/supp-transaction/{idTransaction}")
		@ResponseBody
		public void suppTransaction(@PathVariable("idTransaction") long idTransaction){
			TransactionCredit t = transactionCreditService.retrieveTransaction(idTransaction);
			transactionCreditService.deleteTransaction(t);
			}

	
}

	
	
	

