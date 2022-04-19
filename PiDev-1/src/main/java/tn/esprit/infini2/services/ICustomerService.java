package tn.esprit.infini2.services;

import java.util.List;

import tn.esprit.infini2.entities.Customer;

public interface ICustomerService {
	List<Customer> retrieveAllCustomers();

	Customer addCustomer(Customer c);

	void deleteCustomer(Long id);

	Customer updateCustomer(Customer u);

	Customer retrieveCustomer(Long id);

	List<Customer> retrieveAllCustomers(Long id);
}
