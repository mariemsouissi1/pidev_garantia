package tn.esprit.infini2.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import tn.esprit.infini2.entities.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long>{

}

