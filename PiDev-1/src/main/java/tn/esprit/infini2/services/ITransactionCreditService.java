package tn.esprit.infini2.services;

import java.util.List;
import java.util.Map;

import tn.esprit.infini2.entities.TransactionCredit;
import tn.esprit.infini2.exceptions.InvalidAccountException;
import tn.esprit.infini2.exceptions.InvalidAmountException;
import tn.esprit.infini2.exceptions.InvalidBalanceException;




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
