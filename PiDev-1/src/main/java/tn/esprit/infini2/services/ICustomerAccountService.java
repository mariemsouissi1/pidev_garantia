package tn.esprit.infini2.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import tn.esprit.infini2.entities.Customer;
import tn.esprit.infini2.entities.CustomerAccount;

public interface ICustomerAccountService {
	List<CustomerAccount> retrieveAllCustomerAccounts();
	CustomerAccount addCustomerAccount(CustomerAccount c);
	void deleteCustomerAccount(Long id);
	CustomerAccount updateCustomerAccount(CustomerAccount u);
	CustomerAccount retrieveCustomerAccount(Long id);
	int calculScoreSalary(Customer customer);
	float calculScoreGender(Customer customer);
	LocalDate convertToLocalDateViaInstant(Date dateToConvert);
	float calculScoreAge(Customer customer);
	Float calculScore(Customer customer);
}
