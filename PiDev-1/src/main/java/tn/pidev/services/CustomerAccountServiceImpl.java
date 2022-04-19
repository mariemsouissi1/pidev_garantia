package tn.pidev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.pidev.entities.CustomerAccount;
import tn.pidev.repositories.CustomerAccountRepository;

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
