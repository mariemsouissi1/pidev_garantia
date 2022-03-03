package tn.esprit.infini2.services;

import java.util.List;

import tn.esprit.infini2.entities.employee;

public interface IEmployeeService {
	List<employee> retrieveAllCustomerAccounts();

	employee addEmployee(employee c);

	void deleteEmployee(Long id);

	employee updateEmployee(employee u);

	employee retrieveEmployee(Long id);
}
