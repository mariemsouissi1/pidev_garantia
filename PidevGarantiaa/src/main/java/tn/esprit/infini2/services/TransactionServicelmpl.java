package tn.esprit.infini2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.infini2.entities.Transaction;
import tn.esprit.infini2.repositories.TransactionRepository;

@Service
public class TransactionServicelmpl implements ITransactionService{
	@Autowired
	TransactionRepository transactionRepository;
	@Override
	public List<Transaction> retrieveAllTransaction() {
		return (List<Transaction>) transactionRepository.findAll();
	}

	@Override
	public Transaction addTransaction(Transaction t) {
		transactionRepository.save(t);
		return t;
	}

	@Override
	public void deleteTransaction(Long id) {
		transactionRepository.deleteById(id);		
	}

	@Override
	public Transaction updateTransaction(Transaction u) {
		transactionRepository.save(u);
		return u;
	}

	@Override
	public Transaction retrieveTransaction(Long id) {
		
		return transactionRepository.findById(id).orElse(null);
	}

}
