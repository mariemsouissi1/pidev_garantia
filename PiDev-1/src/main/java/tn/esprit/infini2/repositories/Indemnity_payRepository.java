package tn.esprit.infini2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.infini2.entities.Contract;
import tn.esprit.infini2.entities.Indemnity_pay;

@Repository
public interface Indemnity_payRepository extends CrudRepository<Indemnity_pay, Integer> {
	@Query(value = "SELECT * FROM Indemnity_pay Ip WHERE Ip.contract_contract_id= ?1 " , nativeQuery = true)
    List<Indemnity_pay >findAllByContract(Contract c ) ; 

}
