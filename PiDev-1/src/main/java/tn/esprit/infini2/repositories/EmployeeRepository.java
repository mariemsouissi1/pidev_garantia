package tn.esprit.infini2.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.infini2.entities.employee;

@Repository
public interface EmployeeRepository extends CrudRepository<employee, Long>{

}
