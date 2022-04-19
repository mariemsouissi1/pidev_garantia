package tn.esprit.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.entities.Credit;
import tn.esprit.entities.StatutTransaction;
import tn.esprit.entities.TransactionCredit;
import tn.esprit.exceptions.InvalidAccountException;
import tn.esprit.exceptions.InvalidAmountException;
import tn.esprit.exceptions.InvalidBalanceException;
import tn.esprit.repositories.TransactionCreditRepository;

import java.math.BigDecimal; 


@Service
@Slf4j
public class TransactionCreditServicelmpl implements ITransactionCreditService{
	
	private static final Logger log = LoggerFactory.getLogger(CreditServicelmpl.class);
	
	@Autowired
	TransactionCreditRepository transactionCreditRepository;
	@Autowired 
	IEmailServiceCredit iEmailService;
	
	@Autowired
	ICreditService CreditService;
	
/////////////////////////////////ADD//////////////////////////////////////////////
	
		@Transactional
	    @Override
	    public TransactionCredit createNewVirement(TransactionCredit virement, Long emetteur, Long recepteur, Long idCredit) throws InvalidAccountException,InvalidAmountException,InvalidBalanceException {

	        Credit c = CreditService.retrieveCredit(idCredit);
	        TransactionCredit transaction = null;
	        
			if(emetteur.compareTo(recepteur)==0) {
	            throw new InvalidAccountException("Virement Impossible Compte emetteur et Compte Recepteur ont le meme numero de Compte Veuillez specifier un numero de Compte different");
	        }
	      
	        if (virement.getSourceAccountNumber()!= emetteur)
	        {
	            throw new InvalidAccountException("Votre Compte n'est plus disponible pour l'operation veuillez contacter votre agence");
	        }
	        else if(virement.getDestinedAccountNumber()!= recepteur){
	            throw new InvalidAccountException("Compte Destinataire n'est plus disponible pour l'operation veuillez contacter votre agence");
	        }
	        else{

	        	Date date = new Date(System.currentTimeMillis());
	    		virement.setDateTransaction(date);
	            virement.setDestinedAccountNumber(recepteur);

	            if ( virement.getAmountTransaction().longValue() <=0 || virement.getAmountTransaction().longValue()< 100){
	                throw new InvalidAmountException("Montant specifié null et/ou negative et/ou inférieure à 100");
	            }

	            if ( virement.getSoldeCompteEmetteur().compareTo(virement.getAmountTransaction().subtract(BigDecimal.ONE) ) == 1 ){

	            	virement.setSoldeCompteEmetteur(virement.getSoldeCompteEmetteur().subtract(virement.getAmountTransaction()));

	            	virement.setSoldeCompteRecepteur(virement.getSoldeCompteRecepteur().add(virement.getAmountTransaction()));
	            	
	            	c.setAmountRemainingCredit(c.getAmountCredit()-virement.getAmountTransaction().floatValue());

	                virement.setStatutTransaction(StatutTransaction.verified);
	                
	                c.getTransactions().add(virement);
	                
	            	transaction = transactionCreditRepository.save(virement);
	                

	            }else {
	                throw new InvalidBalanceException("Impossible d'effectuer le virement solde insuffisant");
	            }

	        }

	        return transaction;
		}
	


	
//////////////////////UPDATEEE/////////////////////////////////////::
	@Override
	public TransactionCredit updateTransaction(TransactionCredit t, long idTransaction) 	{
		log.info("Inside update transaction");
		if (transactionCreditRepository.findById(idTransaction).isPresent()){
			TransactionCredit transaction= transactionCreditRepository.findById(idTransaction).get();
			t.setSourceAccountNumber(transaction.getSourceAccountNumber());
			t.setDestinedAccountNumber(transaction.getDestinedAccountNumber());
			t.setCreditTransaction(transaction.getCreditTransaction());
			t.setDescription(transaction.getDescription());
			t.setSoldeCompteEmetteur(transaction.getSoldeCompteEmetteur());
			t.setSoldeCompteRecepteur(transaction.getSoldeCompteRecepteur());
			t.setDateTransaction(transaction.getDateTransaction());
			t.setStatutTransaction(transaction.getStatutTransaction());
			transactionCreditRepository.delete(transaction);
			return transactionCreditRepository.save(t);
		}
		else
		{
			log.info("Transaction not found");
			return null;
		}
	}
	
//////////////////GET BY ID///////////////////////////////////
	@Override
	public TransactionCredit retrieveTransaction(long idTransaction) {
		log.info("Inside retrieveTransaction ");
		return transactionCreditRepository.findById(idTransaction).orElse(null);		
	}
	
////////////////////////GET ALL///////////////////////////
	@Override
	public List<TransactionCredit> retrieveAllTransactions() {
		log.info("Inside retrieveAllTransactions ");
		List <TransactionCredit> transactions = (List<TransactionCredit>) transactionCreditRepository.findAll();
		for (TransactionCredit t : transactions) {
			log.info("List of transactions : "+ t);
		}
		return transactions;
	}
	
//////////////GET ALL SORTED////////////////////////////
	@Override
	public List<TransactionCredit> listAllTransactions() {
		log.info("Inside listAllTransactions sorted ");
		return transactionCreditRepository.findAll(Sort.by("dateTransaction").ascending());

	}
	
//////////////////////////DELETE TRANSACTION////////////////////////////////////
	@Override
	public void deleteTransaction(TransactionCredit t) {
		 
		log.info("Inside delete transaction ");
		StatutTransaction [] statuttransaction = StatutTransaction.values();
		for (StatutTransaction s : statuttransaction) {
			if (s.toString().toUpperCase() == "verified".toUpperCase()) {
				transactionCreditRepository.delete(t);	
			} 
			else {
				log.error("Unverified transaction");
				iEmailService.sendEmailUnverifiedTransaction(t.getCreditTransaction().getCustomerCredit().getEmailAccount(), t);
			}
	}
	}
////////////////////////////////////
	//@SuppressWarnings("null")
	@Override
	public List<TransactionCredit> listTransactionByCustomerAccountId(long idCustomerAccount) {
		log.info("Inside liste transaction pour chaque customer dont id est idCustomerAccount");		
		List<TransactionCredit> L = transactionCreditRepository.findAll(Sort.by("dateTransaction").ascending());
		List<TransactionCredit> ListCustomerAccount = new ArrayList<TransactionCredit>();
		for (int i = 0; i < L.size(); i++) {
			if (L.get(i).getCreditTransaction().getCustomerCredit().getIdCustomerAccount() == idCustomerAccount) {
				ListCustomerAccount.add(L.get(i));
			}
		}
		return ListCustomerAccount;
	}

////////////////LIST TRANSACTION PER YEARRRRRRR/////////////////////////////////////////////////
	@Transactional
	@Override
	public List<TransactionCredit> listTransactionByYear(double year) {
		log.info("Inside list of transactions by year ");
		List<TransactionCredit> L = transactionCreditRepository.findAll(Sort.by("dateTransaction").ascending());
		List<TransactionCredit> ListTransactionYear = new ArrayList<TransactionCredit>();
		for (int i = 0; i < L.size(); i++) {
			log.info("YEAR " + Double.parseDouble(new SimpleDateFormat("yyyy").format(L.get(i).getDateTransaction())) + "\n");
			if (Double.parseDouble(new SimpleDateFormat("yyyy").format(L.get(i).getDateTransaction())) == year) {
				
				@SuppressWarnings("unused")
				String datetoString = String.format("%1$tY-%1$tm-%1$td", L.get(i).getDateTransaction());

				ListTransactionYear.add(L.get(i));

				log.info("Adding year " + Double.parseDouble(new SimpleDateFormat("yyyy").format(L.get(i).getDateTransaction())));
			}
		}
		return ListTransactionYear;
	}
	
/////////////////DICT (Month : AmountTransaction) //////////////////////
	@Override
	public Map<Double, Double> StatisticMonthByAmount(double year) {
		log.info("Inside StatisticMonthByAmount");
		Map<Double, Double> MonthAmount = new HashMap<Double, Double>();
		double sum1 = 0;
		double sum2 = 0;
		double sum3 = 0;
		double sum4 = 0;
		double sum5 = 0;
		double sum6 = 0;
		double sum7 = 0;
		double sum8 = 0;
		double sum9 = 0;
		double sum10 = 0;
		double sum11 = 0;
		double sum12 = 0;
		List<TransactionCredit> L = listTransactionByYear(year);
		for (int i = 0; i < L.size(); i++) {
			String datetoString = String.format("%1$tY-%1$tm-%1$td", L.get(i).getDateTransaction());
			String[] dateParts = datetoString.split("-");
			String month = dateParts[1];
			if (month.equals("01"))

			{
				log.info("Amount"+L.get(i).getAmountTransaction());
				sum1 += L.get(i).getAmountTransaction().doubleValue();
				log.info("Sum Amount"+L.get(i).getAmountTransaction());
				MonthAmount.put( (double) 1, sum1);
			}

			else if (month.equals("02")) {
				sum2 += L.get(i).getAmountTransaction().doubleValue();
				MonthAmount.put((double) 2, sum2);
			}

			else if (month.equals("03")) {
				sum3 += L.get(i).getAmountTransaction().doubleValue();
				MonthAmount.put((double) 3, sum3);
			}

			else if (month.equals("04")) {
				sum4 += L.get(i).getAmountTransaction().doubleValue();
				MonthAmount.put((double) 4, sum4);
			}

			else if (month.equals("05")) {
				sum5 += L.get(i).getAmountTransaction().doubleValue();
				MonthAmount.put((double) 5, sum5);
			}

			else if (month.equals("06")) {
				sum6 += L.get(i).getAmountTransaction().doubleValue();
				MonthAmount.put((double) 6, sum6);
			}

			else if (month.equals("07")) {
				sum7 += L.get(i).getAmountTransaction().doubleValue();
				MonthAmount.put((double) 7, sum7);
			}

			else if (month.equals("08")) {
				sum8 += L.get(i).getAmountTransaction().doubleValue();
				MonthAmount.put((double) 8, sum8);
			}

			else if (month.equals("09")) {
				sum9 += L.get(i).getAmountTransaction().doubleValue();
				MonthAmount.put((double) 9, sum9);
			}

			else if (month.equals("10")) {
				sum10 += L.get(i).getAmountTransaction().doubleValue();
				MonthAmount.put((double) 10, sum10);
			}

			else if (month.equals("11")) {
				sum11 += L.get(i).getAmountTransaction().doubleValue();
				MonthAmount.put((double) 11, sum11);
			}

			else if (month.equals("12")) {
				sum12 += L.get(i).getAmountTransaction().doubleValue();
				MonthAmount.put((double) 12, sum12);
			}

		}

		return MonthAmount;
	}

/////////////////////////////SUM TRANSACTION/////////////////////////////////////////////////////
	
	@Override
	public double SumTransAmount() {
		log.info("Inside SumTransAmount ");
		List<TransactionCredit> transaction = (List<TransactionCredit>) transactionCreditRepository.findAll();
		double sum=0;
		Iterator<TransactionCredit> iter = transaction.iterator();
		while (iter.hasNext()) {		
			TransactionCredit trans = iter.next(); 
		    sum=sum+trans.getAmountTransaction().doubleValue();
		}
		return sum;
	}


}


