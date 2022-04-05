package tn.esprit.infini2.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.infini2.entities.Customer;
import tn.esprit.infini2.repositories.CustomerRepository;

@Service
public class CustomerServiceImp implements ICustomerService{

	@Autowired
	CustomerRepository customerRepository;
	@Override
	public List<Customer> retrieveAllCustomers() {
		List<Customer> list = new ArrayList<>();
		Iterable<Customer> items = customerRepository.findAll();
		items.forEach(list::add);
		return list;
	}

	public Long countCustomer() {
		return customerRepository.count();
	}

	@Override
	public Customer addCustomer(Customer c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCustomer(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Customer updateCustomer(Customer u) {
		customerRepository.save(u);
		return u;
	}

	@Override
	public Customer retrieveCustomer(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> retrieveAllCustomers(Long id) {
		List<Customer> list=customerRepository.findAll();
		return list;
	}

}