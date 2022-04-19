package tn.esprit.infini2.services;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.mail.MailException;

import tn.esprit.infini2.entities.Bank;
import tn.esprit.infini2.entities.LoanSimulation;
import tn.esprit.infini2.entities.Offer;






public interface ILoanSimulationService {

	List<LoanSimulation> getAllSimulations();
	List<LoanSimulation> getAllSimulationsByCin(long cin);
	LoanSimulation getSimulationById(long idLoan);
	List<LoanSimulation> getAllSimulationsByNameBank(String nameBank);

	LoanSimulation addSimulation(String nameBank,int nbrAnnee,long idOffer,double salaireCustomer,long idCustomerAccount) throws MailException, MessagingException;
	
	void deleteSimulationById(long id);
	void deleteOrNotifSimulationInScheduling() throws MailException, MessagingException;
	
	void confirmSimulation(long idSimulation) throws MailException, MessagingException;
	void unConfirmSimulation(long idSimulation) throws MailException, MessagingException;
	
	//////////////methode de calcul des simulations////////////////
	LoanSimulation simulate(String nameBank, int nbrAnnee, long idOffer, double salaireClient);
	double calculTaux(Bank bank);
	double calculTauxMensuel(Bank bank);
	double calculNbrEcheance(int nbrAnnee); 
	double calculCapaciteDeRemboursement(double salaire);
	double calculInteret(Offer offer,Bank bank);
	double calculMensualite(Offer offer,Bank bank,int nbrAnnee);
	double calculPrincipale(Offer offer, Bank bank,int nbrAnnee);
	double calculMontantRembourse(Offer offer, Bank bank,int nbrAnnee);
	double calculInteretTotale(Offer offer, Bank bank,int nbrAnnee);
	
}
