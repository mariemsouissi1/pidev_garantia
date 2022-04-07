package tn.esprit.infini2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import tn.esprit.infini2.entities.Contract;
import tn.esprit.infini2.entities.Type_Contract;

@Repository
public interface ContractRepository extends CrudRepository<Contract, Long>{
	
	@Query("FROM Contract c WHERE c.typeContract = :typeContract")
	 List<Contract> findContractByType(@Param(value = "typeContract") Type_Contract typeContract);
	
	@Query("FROM Contract c WHERE c.typeContract = :typeContract")
	 List<Contract> countContractByType(@Param(value = "typeContract") Type_Contract typeContract);
	
	@Query("FROM Contract c WHERE c.idContract = :idContract")
	Contract findContractByID(@Param(value = "idContract") Long idContract);

}
