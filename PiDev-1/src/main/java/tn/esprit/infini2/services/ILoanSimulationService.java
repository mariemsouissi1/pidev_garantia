package tn.pidev.services;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.mail.MailException;

import tn.pidev.entities.Bank;
import tn.pidev.entities.LoanSimulation;
import tn.pidev.entities.Offer;

//import tn.esprit.spring.entities.Ad;


public interface ILoanSimulationService {

	public List<LoanSimulation> getAllSimulations();
	public List<LoanSimulation> getAllSimulationsByCin(long cin);
	public LoanSimulation getSimulationById(long idLoan);
	public List<LoanSimulation> getAllSimulationsByNameBank(String nameBank);

	public LoanSimulation addSimulation(String nameBank,int nbrAnnee,long idOffer,double salaireCustomer,long idCustomerAccount) throws MailException, MessagingException;
	
	public void deleteSimulationById(long id);
	public void deleteOrNotifSimulationInScheduling(long idAgent) throws MailException, MessagingException;
	
	public void confirmSimulation(long idSimulation) throws MailException, MessagingException;
	public void unConfirmSimulation(long idSimulation) throws MailException, MessagingException;
	
	//////////////methode de calcul des simulations////////////////
	public LoanSimulation simulate(String nameBank, int nbrAnnee, long idOffer, double salaireClient);
	public double calculTaux(Bank bank);
	public double calculTauxMensuel(Bank bank);
	public double calculNbrEcheance(int nbrAnnee); 
	public double calculCapaciteDeRemboursement(double salaire);
	public double calculInteret(Offer offer,Bank bank);
	public double calculMensualite(Offer offer,Bank bank,int nbrAnnee);
	public double calculPrincipale(Offer offer, Bank bank,int nbrAnnee);
	public double calculMontantRembourse(Offer offer, Bank bank,int nbrAnnee);
	public double calculInteretTotale(Offer offer, Bank bank,int nbrAnnee);
	
}
