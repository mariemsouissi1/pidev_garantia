package tn.pidev.controllers;

import java.io.FileNotFoundException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.NoSuchPaddingException;
import javax.validation.Valid;

import org.apache.tomcat.util.net.openssl.ciphers.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/transaction")
public class TransactionCreditController {

//	@Autowired
//	ITransactionCreditService transactionCreditService;
//	@Autowired 
//	IEmailService iEmailService; 
//	@Autowired
//	SMSServiceCredit service;
//	
///////////////////////:::request for transaction /////////
//	//
//	@GetMapping("/requesttransaction")
//	public String requestTransactionOnline(@Valid @RequestBody SMSRequest smsRequest, @RequestBody TransactionCredit t) {
//		if (t.getTypeTransaction().toStringTypeTrans() == "online") {
//			
//			return service.sendSms(smsRequest);
//			
//		}
//		if (t.getTypeTransaction().toStringTypeTrans() == "versement") {
//			
//			return null;
//		}
//		return null;
//	}


}

	//public Transaction record 
/////////////////////////////////tzid transaction//////////////////////////////////////////////
	// http://localhost:8087/pidevmariem/credit/request-credit-id/3
//	@PostMapping("/add_transaction")
//	@ResponseBody
//    public Transaction recordTransaction(@RequestBody Transaction transaction) throws FileNotFoundException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
//        long sourceAccountNumber = transaction.getSourceAccountNumber();
//        long destinedAccountNumber = transaction.getDestinedAccountNumber();
////Pour dechiffrer
////        Encryption e = new Encryption();
//        transaction.setSourceAccountNumber(e.decrypt(sourceAccountNumber));
//        transaction.setDestinedAccountNumber(e.decrypt(destinedAccountNumber));
//        transactionService.addTransaction(transaction);
//
//        return transaction;
//    }

	
//
//	import org.springframework.beans.factory.annotation.Autowired;
//	import org.springframework.data.rest.webmvc.BasePathAwareController;
//	import org.springframework.data.rest.webmvc.RepositoryRestController;
//	import org.springframework.web.bind.annotation.PostMapping;
//	import org.springframework.web.bind.annotation.RequestBody;
//	import org.springframework.web.bind.annotation.RequestMapping;
//	import org.springframework.web.bind.annotation.ResponseBody;
//
//	import javax.crypto.NoSuchPaddingException;
//	import java.io.FileNotFoundException;
//	import java.security.InvalidKeyException;
//	import java.security.NoSuchAlgorithmException;
//
//	@BasePathAwareController
//	public class TransactionController {
//
//	    private final TransactionRepository transactionRepository;
//
//	    public TransactionController(final TransactionRepository transactionRepository) {
//	        this.transactionRepository = transactionRepository;
//	    }
//
//	    @PostMapping("/transactions")
//	    @ResponseBody
//	    public Transaction recordTransaction(@RequestBody Transaction transaction) throws FileNotFoundException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
//	        String accountNumber = transaction.getAccountNumber();
//	        String accountFrom = transaction.getAccountFrom();
//Pour déchiffré
//	        Encryption encryption = new Encryption();
//	        transaction.setAccountFrom(encryption.decrypt(accountFrom));
//	        transaction.setAccountNumber(encryption.decrypt(accountNumber));
//	        transactionRepository.save(transaction);

//	        return transaction;
//	    }
//	}	
	
	
	
	

