package tn.esprit.infini2.services;

import java.util.List;

import tn.esprit.infini2.entities.Customer;
import tn.esprit.infini2.entities.CustomerAccount;

public interface ICustomerAccountService {
	List<CustomerAccount> retrieveAllCustomerAccounts();

	CustomerAccount addCustomerAccount(CustomerAccount c);

	void deleteCustomerAccount(Long id);

	CustomerAccount updateCustomerAccount(CustomerAccount u);

	CustomerAccount retrieveCustomerAccount(Long id);

	Float calculScore(Customer customer);


}
