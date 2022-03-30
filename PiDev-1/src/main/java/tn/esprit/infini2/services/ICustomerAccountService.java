package tn.esprit.infini2.services;

import java.util.List;

import tn.esprit.infini2.entities.Customer;
import tn.esprit.infini2.entities.customerAccount;

public interface ICustomerAccountService {
	List<customerAccount> retrieveAllCustomerAccounts();

	customerAccount addCustomerAccount(customerAccount c);

	void deleteCustomerAccount(Long id);

	customerAccount updateCustomerAccount(customerAccount u);

	customerAccount retrieveCustomerAccount(Long id);

	Float calculScore(Customer customer);
}
