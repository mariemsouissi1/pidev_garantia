package tn.esprit.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.entities.CustomerAccount;

@Repository
public interface CustomerAccountRepository extends CrudRepository<CustomerAccount, Long> {

}
