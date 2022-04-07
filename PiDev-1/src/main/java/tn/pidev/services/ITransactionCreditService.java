package tn.pidev.services;

import java.util.List;
import java.util.Map;

import tn.pidev.entities.TransactionCredit;
import tn.pidev.exceptions.InvalidAccountException;
import tn.pidev.exceptions.InvalidAmountException;
import tn.pidev.exceptions.InvalidBalanceException;


public interface ITransactionCreditService {
	
	List<TransactionCredit> retrieveAllTransactions();

	TransactionCredit createNewVirement(TransactionCredit virement, Long emetteur, Long recepteur, Long idCredit) throws InvalidAccountException,InvalidAmountException,InvalidBalanceException;

	void deleteTransaction(TransactionCredit t);

	TransactionCredit updateTransaction(TransactionCredit t, long idTransaction);

	TransactionCredit retrieveTransaction(long idTransaction);

	List<TransactionCredit> listTransactionByYear(double year);
	
	Map<Double, Double> StatisticMonthByAmount(double year);

	double SumTransAmount();

	List<TransactionCredit> listAllTransactions();

	public List<TransactionCredit> listTransactionByCustomerAccountId(long idCustomerAccount);

	
}
