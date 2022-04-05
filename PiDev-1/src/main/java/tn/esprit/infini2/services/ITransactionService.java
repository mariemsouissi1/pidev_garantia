package tn.esprit.infini2.services;

import java.util.List;

import tn.esprit.infini2.entities.Transaction;

public interface ITransactionService {
	List<Transaction> retrieveAllTransaction();

	Transaction addTransaction(Transaction t);

	void deleteTransaction(Long id);

	Transaction updateTransaction(Transaction u);

	Transaction retrieveTransaction(Long id);
}
