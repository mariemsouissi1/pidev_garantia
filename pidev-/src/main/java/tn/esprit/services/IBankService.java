package tn.esprit.services;

import java.util.List;

import tn.esprit.entities.Bank;


public interface IBankService {
	
	public String addBank(Bank bank, String NameBank); 
	
	Bank updateBank(long idBank, Bank bank);

	List<Bank> getAllBanks();
	
	Bank getBankById(long idBank);
	
	Bank getBankByName(String nameBank);
	
	void deleteBankById(long idBank);
	
}
