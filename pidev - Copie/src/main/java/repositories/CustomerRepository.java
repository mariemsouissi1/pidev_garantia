package repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import entities.customer;

@Repository
public interface CustomerRepository extends CrudRepository<customer, Long>{

}
