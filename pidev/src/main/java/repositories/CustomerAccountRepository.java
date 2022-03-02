package repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import entities.customerAccount;

@Repository
public interface CustomerAccountRepository extends CrudRepository<customerAccount, Long>{

}
