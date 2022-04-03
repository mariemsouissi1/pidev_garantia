package repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import entities.employee;

@Repository
public interface EmployeeRepository extends CrudRepository<employee, Long>{

}
