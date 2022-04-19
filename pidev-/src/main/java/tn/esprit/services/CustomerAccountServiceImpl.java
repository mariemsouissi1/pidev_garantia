package tn.esprit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.entities.CustomerAccount;
import tn.esprit.repositories.CustomerAccountRepository;

@Service
public class CustomerAccountServiceImpl implements ICustomerAccountService {
	@Autowired
	CustomerAccountRepository customerRepository;
	
	@Override
	public CustomerAccount addCustomerAccount(CustomerAccount ca) {
		customerRepository.save(ca);
		return ca;
	}
}
