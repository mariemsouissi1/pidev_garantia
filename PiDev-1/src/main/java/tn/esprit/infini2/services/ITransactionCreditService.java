package tn.esprit.infini2.services;

import java.util.List;
import java.util.Map;

import tn.esprit.infini2.entities.TransactionCredit;


public interface ITransactionCreditService {
	
	List<TransactionCredit> retrieveAllTransactions();

	TransactionCredit addTransaction(TransactionCredit t);

	void deleteTransaction(TransactionCredit t);

	TransactionCredit updateTransaction(TransactionCredit u);

	TransactionCredit retrieveTransaction(long idTransaction);

	List<TransactionCredit> listTransactionByYear(double year);
	
	Map<Double, Double> StatisticMonthByAmount(double year);

	double SumTransAmount();

	List<TransactionCredit> listAllTransactions();

	public List<TransactionCredit> listTransactionByCustomerAccountId(int idCustomerAccount);

	
}
