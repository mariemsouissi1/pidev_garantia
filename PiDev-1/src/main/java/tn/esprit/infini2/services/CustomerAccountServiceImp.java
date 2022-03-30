package tn.esprit.infini2.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.infini2.entities.Customer;
import tn.esprit.infini2.entities.customerAccount;
import tn.esprit.infini2.repositories.CustomerAccountRepository;

public class CustomerAccountServiceImp implements ICustomerAccountService{
	@Autowired
	CustomerAccountRepository customerAccountRepository;
	@Override
	public List<customerAccount> retrieveAllCustomerAccounts() {
		return (List<customerAccount>) customerAccountRepository.findAll();
	}

	@Override
	public customerAccount addCustomerAccount(customerAccount c) {
		customerAccountRepository.save(c);
		return c;
	}

	@Override
	public void deleteCustomerAccount(Long id) {
		customerAccountRepository.deleteById(id);	
		
	}

	@Override
	public customerAccount updateCustomerAccount(customerAccount u) {
		customerAccountRepository.save(u);
		return u;
	}

	@Override
	public customerAccount retrieveCustomerAccount(Long id) {
		return customerAccountRepository.findById(id).orElse(null);
	}

	@Override
	public Float calculScore(Customer customer) {


		return null;
	}

}
