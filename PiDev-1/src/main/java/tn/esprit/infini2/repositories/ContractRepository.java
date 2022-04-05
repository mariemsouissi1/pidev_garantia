package tn.esprit.infini2.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.infini2.entities.Contract;

@Repository
public interface ContractRepository extends CrudRepository<Contract, Long>{

}
