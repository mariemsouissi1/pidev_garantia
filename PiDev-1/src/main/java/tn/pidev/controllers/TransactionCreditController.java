package tn.pidev.controllers;



import java.util.List;
import java.util.Map;

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

import tn.pidev.entities.Credit;
import tn.pidev.entities.TransactionCredit;
import tn.pidev.exceptions.InvalidAccountException;
import tn.pidev.exceptions.InvalidAmountException;
import tn.pidev.exceptions.InvalidBalanceException;
import tn.pidev.repositories.CreditRepository;
import tn.pidev.services.ITransactionCreditService;



@RestController
@RequestMapping("/transaction")
public class TransactionCreditController {

	@Autowired
	ITransactionCreditService transactionCreditService;

	@Autowired
	CreditRepository CreditRepo;
////////////////////ADD///////////////////:

	// http://localhost:8087/pidevmariem/transaction/add-transaction/id
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
	
	// http://localhost:8087/pidevmariem/transaction/update-transaction/idTransaction
	@PutMapping("/update-transaction/{idTransaction}")
	@ResponseBody
	public TransactionCredit refreshTransaction(@RequestBody TransactionCredit t ,@PathVariable("idTransaction") long idTransaction)
	{
		return transactionCreditService.updateTransaction(t,idTransaction);
	}



////////////////////////GET ALL///////////////////////////

	// http://localhost:8087/pidevmariem/transaction/retrieve-all-transactions/
	@GetMapping("/retrieve-all-transactions")
	@ResponseBody
	public List<TransactionCredit> retrieveAllTransactions(){
		return transactionCreditService.retrieveAllTransactions();
		}
	
//////////////GET ALL SORTED////////////////////////////
	
	// http://localhost:8087/pidevmariem/transaction/retrieve-all-transactions-sorted/
		@GetMapping("/retrieve-all-transactions-sorted")
		@ResponseBody
		public List<TransactionCredit> retrieveAllTransactionsSorted (){
			return transactionCreditService.listAllTransactions();
			}
	
//////////////////GET BY ID///////////////////////////////////

	// http://localhost:8087/pidevmariem/transaction/retrieve-transaction/3
	@GetMapping("/retrieve-transaction/{id-transaction}")
	@ResponseBody
	public TransactionCredit retrieveTransactionById(@PathVariable("id-transaction") long idTransaction){
		return transactionCreditService.retrieveTransaction(idTransaction);
	}

	
	
///////////////////LIST TRANSACTION PER CUSTOMER///////////////////

		// http://localhost:8087/pidevmariem/transaction/listperCustomer/3
		@GetMapping("/listperCustomer/{idCustomer}")
		@ResponseBody
		public List<TransactionCredit> retrieveTransactionsByCustomer(@PathVariable("idCustomer") long idCustomer){
				return transactionCreditService.listTransactionByCustomerAccountId(idCustomer);
			}
	

////////////////LIST TRANSACTION PER YEARRRRRRR/////////////////////////////////////////////////

	// http://localhost:8087/pidevmariem/transaction/list-transactions-per-year/2021
		@GetMapping("/list-transactions-per-year/{year}")
		@ResponseBody
		public List<TransactionCredit> listTransactionsPerYear(@PathVariable("year") double year){
				return transactionCreditService.listTransactionByYear(year);
		}
	
/////////////////DICT (Month : AmountTransaction) //////////////////////

		// http://localhost:8087/pidevmariem/transaction/dict/2021
		@GetMapping("/dict/{year}")
		@ResponseBody
		public Map<Double, Double> getStatisticMonthByAmount(@PathVariable("year") double year) {
			Map<Double, Double> map = transactionCreditService.StatisticMonthByAmount(year);
			return map;
		}
	
/////////////////////////////SUM TRANSACTION/////////////////////////////////////////////////////

		// http://localhost:8087/pidevmariem/transaction/somme/
		@GetMapping("/somme")
		@ResponseBody
		public double SommeTransactions() {
			return transactionCreditService.SumTransAmount();
		}	
	
	
//////////////////////////DELETE TRANSACTION////////////////////////////////////

		// http://localhost:8087/pidevmariem/transaction/supp-transaction/3
		@DeleteMapping("/supp-transaction/{idTransaction}")
		@ResponseBody
		public void suppTransaction(@PathVariable("idTransaction") long idTransaction){
			TransactionCredit t = transactionCreditService.retrieveTransaction(idTransaction);
			transactionCreditService.deleteTransaction(t);
			}

	
}

	
	
	

