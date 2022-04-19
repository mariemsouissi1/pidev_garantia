package tn.pidev.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.pidev.entities.Bank;
import tn.pidev.repositories.BankRepository;



@Service
public class BankServiceImpl implements IBankService {
	
	private static final Logger log = LoggerFactory.getLogger(BankServiceImpl.class);
	@Autowired
	BankRepository bankRepository;
	
/////////////////////////ADD/////////////////////

	@Override
	public String addBank(Bank bank, String NameBank) {
		log.info("Inside Add Bank");
		List<Bank> banks= bankRepository.getAllBanks();
		List<String> bankNames = new ArrayList<>();
		for (int i=0; i<banks.size(); i++) {
			bankNames.add(banks.get(i).getNameBank());
		}
			if (bankNames.contains(NameBank)) {
				return "Bank exists already !" ;
			}
			else {
				bankRepository.save(bank);
				return "Bank added successfullly";
			}
		}

/////////////////////////UPDATE WITH CONTROL/////////////////////

	@Override
	public Bank updateBank(long idBank, Bank bank) {
		 log.info("Inside Update Bank");
		if (bankRepository.findById(idBank).isPresent()){
		Bank Bank = bankRepository.findById(idBank).get();
		bank.setNameBank(Bank.getNameBank());
		bank.setDescriptionBank(Bank.getDescriptionBank());
		bank.setMargeInteretBank(Bank.getMargeInteretBank());
		bank.setLoanSimulatorListBank(Bank.getLoanSimulatorListBank());
		return bankRepository.save(bank);
		}
		else {
			log.error("Bank not found");
			return null;
		}
	}


/////////////////////////GET/////////////////////

	@Override
	public List<Bank> getAllBanks() {
		 log.info("Inside Get All Banks");
		return bankRepository.getAllBanks();
	}
	
	@Override
	public Bank getBankById(long idBank) {	
		 log.info("Inside Get Bank By Id");
		return bankRepository.getBankById(idBank);
	}
	
	@Override
	public Bank getBankByName(String nameBank) {
		 log.info("Inside Get Bank By Name");
		return  bankRepository.getBankByName(nameBank);
	}

	
/////////////////////////DELETE/////////////////////

	@Override
	public void deleteBankById(long idBank) {
		log.info("Inside Delete Bank");
		Bank bank = bankRepository.findById(idBank).get();
		bankRepository.delete(bank);
		
	}

}
