package tn.esprit.infini2.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import tn.esprit.infini2.entities.Employee;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long>{
    Optional<Employee> findEmployeeByEmail(String email);
}

