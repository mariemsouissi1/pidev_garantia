package tn.esprit.infini2.services;

import java.util.List;

import tn.esprit.infini2.entities.customer;

public interface ICustomerService {
	List<customer> retrieveAllCustomers();

	customer addCustomer(customer c);

	void deleteCustomer(Long id);

	customer updateCustomer(customer u);

	customer retrieveCustomer(Long id);
}
