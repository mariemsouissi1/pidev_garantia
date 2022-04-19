package tn.esprit.infini2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import tn.esprit.infini2.entities.Contract;
import tn.esprit.infini2.entities.Customer;
import tn.esprit.infini2.entities.Employee;
import tn.esprit.infini2.entities.Type_Contract;

@Repository
public interface ContractRepository extends CrudRepository<Contract, Long>{
	
	@Query("FROM Contract c WHERE c.typeContract = :typeContract")
	 List<Contract> findContractByType(@Param(value = "typeContract") Type_Contract typeContract);
	
	@Query("FROM Contract c WHERE c.typeContract = :typeContract")
	 List<Contract> countContractByType(@Param(value = "typeContract") Type_Contract typeContract);
	
	@Query("FROM Contract c WHERE c.idContract = :idContract")
	Contract findContractByID(@Param(value = "idContract") Long idContract);

	@Query("SELECT c FROM Contract c join c.C_customerAccount ca join ca.sinister s  "
			+ "where s.idSinister= :idSinister")
	Contract retrieveContract(@Param("idSinister") Integer idSinister);
	@Query(value = "SELECT * FROM contract c WHERE c.c_customer_account_id_customer_account= ?1 " , nativeQuery = true)
	List<Contract> findAllByEmployee(Employee employee) ; 
	
	
	@Query(value = "SELECT * FROM contract c WHERE c.c_customer_account_id_customer_account= ?1 " , nativeQuery = true)
	List<Contract> findAllByCustomer(Customer customer) ; 
}
