package tn.esprit.infini2.services;

import java.util.List;

import tn.esprit.infini2.entities.Employee;

public interface IEmployeeService {
	List<Employee> retrieveAllCustomerAccounts();

	Employee addEmployee(Employee c);

	void deleteEmployee(Long id);

	Employee updateEmployee(Employee u);

	Employee retrieveEmployee(Long id);
}
