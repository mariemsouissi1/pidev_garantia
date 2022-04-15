package tn.esprit.infini2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.infini2.entities.Employee;
import tn.esprit.infini2.repositories.CustomerRepository;
import tn.esprit.infini2.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImp implements IEmployeeService{

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public List<Employee> retrieveAllCustomerAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee addEmployee(Employee c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteEmployee(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employee updateEmployee(Employee u) {
		employeeRepository.save(u);
		return u;
	}

	@Override
	public Employee retrieveEmployee(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
