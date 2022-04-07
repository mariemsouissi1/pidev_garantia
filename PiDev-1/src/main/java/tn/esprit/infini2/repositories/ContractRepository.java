package tn.esprit.infini2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.infini2.entities.Contract;
import tn.esprit.infini2.entities.Customer;
import tn.esprit.infini2.entities.Employee;

@Repository
public interface ContractRepository extends CrudRepository<Contract, Long>{
	@Query(value = "SELECT * FROM contract c WHERE c.c_customer_account_id_customer_account= ?1 " , nativeQuery = true)
	List<Contract> findAllByEmployee(Employee employee) ; 
	
	
	@Query(value = "SELECT * FROM contract c WHERE c.c_customer_account_id_customer_account= ?1 " , nativeQuery = true)
	List<Contract> findAllByCustomer(Customer customer) ; 
	
	
	
	@Query("SELECT c FROM Contract c join c.C_customerAccount ca join ca.sinister s  "
			+ "where s.idSinister= :idSinister")
	Contract retrieveContract(@Param("idSinister") Integer idSinister);


}
