package tn.pidev.services;

import java.util.List;

import tn.pidev.entities.Bank;


public interface IBankService {
	
	long addBank(Bank bank);
	
	Bank updateBank(long idBank, Bank bank);

	List<Bank> getAllBanks();
	
	Bank getBankById(long idBank);
	
	Bank getBankByName(String nameBank);
	
	void deleteBankById(long idBank);
	
}
