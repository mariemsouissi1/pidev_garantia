package tn.esprit.infini2.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.infini2.entities.Customer;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>{

    Optional<Customer> findCustomerByEmail(String email);
}
