package tn.pidev.services;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;



import tn.pidev.entities.Agent;
import tn.pidev.entities.Bank;
import tn.pidev.entities.CustomerAccount;
import tn.pidev.entities.LoanSimulation;
import tn.pidev.entities.Offer;
import tn.pidev.entities.StatutLoanSimulation;
import tn.pidev.repositories.AgentRepository;
import tn.pidev.repositories.BankRepository;
import tn.pidev.repositories.CustomerAccountRepository;
import tn.pidev.repositories.LoanSimulationRepository;
import tn.pidev.repositories.OfferRepository;



@Service
@Configuration
@ConditionalOnProperty(name="scheduling.enabled",matchIfMissing=true)
public class LoanSimulationServiceImpl implements ILoanSimulationService {

	@Autowired
	LoanSimulationRepository loanSimulationRepository;

	@Autowired
	BankRepository bankRepository;

	@Autowired
	OfferRepository offerRepository;
	
	@Autowired 
	CustomerAccountRepository customerAccountRepository;
	
	@Autowired
	AgentRepository agentRepository;


	@Autowired
	IPdfLoanService iPdfService;

	@Autowired
	IMailService iMailService;

/////////////////////////////////////Retrieve data from DB///////////////////////////////////////////////////////////

	@Override
	public List<LoanSimulation> getAllSimulations() {
		return loanSimulationRepository.findAll();
	}

	@Override
	public List<LoanSimulation> getAllSimulationsByCin(long cin) {
		return loanSimulationRepository.getAllSimulationsByCin(cin);
	}

	@Override
	public List<LoanSimulation> getAllSimulationsByNameBank(String nameBank) {

		return loanSimulationRepository.getAllSimulationsByNameBank(nameBank);
	}

	@Override
	public LoanSimulation getSimulationById(long idLoan) {

		return loanSimulationRepository.findById(idLoan).get();
	}

///////////////////////////////Add simulation with sending mails////////////////////////////

	@Override
	public LoanSimulation addSimulation(String nameBank, int nbrAnnee,long idOffer, double salaireCustomer, long idCustomerAccount)
			throws MailException, MessagingException {
		
		LoanSimulation simulation = simulate(nameBank, nbrAnnee, idOffer, salaireCustomer);
		CustomerAccount customer = customerAccountRepository.findById(idCustomerAccount).get();
		Agent agent = agentRepository.getAgentByNameBank(nameBank);
		simulation.setCustomerAccountLoan(customer);
		simulation.getBankLoan().setAgentBank(agent);
		simulation.setStatusLoanSimulation(StatutLoanSimulation.inprogress); 
		loanSimulationRepository.save(simulation);
		iMailService.sendWithAttachment(customer, agent, iPdfService.toPDF(simulation.getIdLoan()));

		return (simulation);
	}


//////////////////////////Different Methods to delete simulation///////////////////////////////////////
	@Override
	public void deleteSimulationById(long idLoan) {
		LoanSimulation l=loanSimulationRepository.findById(idLoan).get();
		l.getBankLoan().setAgentBank(null);
		l.setBankLoan(null);
		l.setCustomerAccountLoan(null);
		loanSimulationRepository.delete(idLoan);
	}

	@Override
	@Scheduled(cron="0 * * * *")
	public void deleteOrNotifSimulationInScheduling(long idAgent) throws MailException, MessagingException {

		List<LoanSimulation> list1 = loanSimulationRepository.getAllSimulationsDenied();
		List<LoanSimulation> list2= loanSimulationRepository.getAllSimulationsInProgress();
		Agent agent=agentRepository.getById(idAgent);
		
		if(!list1.isEmpty()){
					
			for (LoanSimulation simulation : list1) {
				deleteSimulationById(simulation.getIdLoan());
			}		
		}
		
		else if(!list2.isEmpty()){

				int nbr = loanSimulationRepository.countAllSimulationsInProgress();
				System.out.println(nbr);
				//iMailService.sendEmailNotifAgent(agent, nbr);
				
		}	

	}

	

//////////////////////////Methods of confirm the request of simulations//////////////////////////////////////////////////

	@Override
	public void confirmSimulation(long idSimulation) throws MailException, MessagingException {

		LoanSimulation simulation = loanSimulationRepository.findById(idSimulation).get();
		CustomerAccount  customer = simulation.getCustomerAccountLoan();
		simulation.setStatusLoanSimulation(StatutLoanSimulation.confirmed); 
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(System.currentTimeMillis());
		//simulation.setS(formatter.format(date));
		loanSimulationRepository.save(simulation);
		//iMailService.sendEmailConfirmation(customer, idSimulation);
		
//		LoansSimulationBank simulation=loanRepository.findById(idloan).get();
//		simulation.setStatus("CONFIRMED");
//		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
//		Date date = new Date(System.currentTimeMillis());
//		simulation.setStartedDate(formatter.format(date));
//		loanRepository.save(simulation);
	}

	@Override
	public void unConfirmSimulation(long idSimulation) throws MailException, MessagingException {

		LoanSimulation simulation = loanSimulationRepository.findById(idSimulation).get();
		CustomerAccount  customer = simulation.getCustomerAccountLoan();
		simulation.setStatusLoanSimulation(StatutLoanSimulation.denied); 
		loanSimulationRepository.save(simulation);
		//iMailService.sendEmailUnConfirmation(customer, idSimulation);

	}
//////////////////////////////////////////Methods of Calculating simulations///////////////////////////////////

	
	@Override
	public LoanSimulation simulate(String nameBank, int nbrAnnee, long idOffer, double salaireCustomer) {
		LoanSimulation simulation = new LoanSimulation();
		Bank bank = bankRepository.getBankByName(nameBank);
		Offer offer = offerRepository.findById(idOffer).get();
		simulation.setBankLoan(bank);
		simulation.setTaux(calculTaux(bank));
		simulation.setMensuel(calculTauxMensuel(bank));
		simulation.setCapaciteDeRemboursement(calculCapaciteDeRemboursement(salaireCustomer));
		simulation.setMensualite(calculMensualite(offer, bank, nbrAnnee));
		simulation.setInteret(calculInteret(offer, bank));
		simulation.setInteretTotale(calculInteretTotale(offer, bank, nbrAnnee));
		simulation.setPrincipale(calculPrincipale(offer, bank, nbrAnnee));
		simulation.setMontantRembourse(calculMontantRembourse(offer, bank, nbrAnnee));
		simulation.setPrixImmob(offer.getPrice());
		simulation.setSalaire(salaireCustomer);
		return simulation;
		
	}

	@Override
	public double calculTaux(Bank bank) {
		double taux = (bank.getTauxMoyenDuMarche() + bank.getMargeInteretBank()) / 100;
		return taux;
	}

	@Override
	public double calculTauxMensuel(Bank bank) {
		return calculTaux(bank)/12;
	}

	@Override
	public double calculNbrEcheance(int nbrAnnee) {
		return nbrAnnee * 12;
	}

	@Override
	public double calculCapaciteDeRemboursement(double salaire) {
		return (salaire * 0.4);
	}

	@Override
	public double calculInteret(Offer offer, Bank bank) {
		double montant = offer.getPrice();
		double tauxMensuel = calculTauxMensuel(bank);
		return montant * tauxMensuel;
	}

	@Override
	public double calculMensualite(Offer offer, Bank bank, int nbrAnnee) {
		double tauxMensuel = calculTauxMensuel(bank);
		double interet = offer.getPrice() * tauxMensuel;

		double nbrEcheance = calculNbrEcheance(nbrAnnee) * (-1);
		double puissance = Math.pow(1 + tauxMensuel, nbrEcheance);
		double q = 1 - puissance;
		return interet / q;
	}

	@Override
	public double calculPrincipale(Offer offer, Bank bank, int nbrAnnee) {
		double mensualite = calculMensualite(offer, bank, nbrAnnee);
		double interet = calculInteret(offer, bank);
		return mensualite - interet;
	}

	@Override
	public double calculMontantRembourse(Offer offer, Bank bank, int nbrAnnee) {
		return calculNbrEcheance(nbrAnnee) * calculMensualite(offer, bank, nbrAnnee);
	}

	@Override
	public double calculInteretTotale(Offer offer, Bank bank, int nbrAnnee) {
		return calculMontantRembourse(offer, bank, nbrAnnee) - offer.getPrice();
	}

	


}
