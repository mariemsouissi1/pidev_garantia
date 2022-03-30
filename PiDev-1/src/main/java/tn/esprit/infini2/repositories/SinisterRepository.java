package tn.esprit.infini2.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.infini2.entities.Contract;
import tn.esprit.infini2.entities.Sinister;

@Repository
public interface SinisterRepository extends CrudRepository<Sinister, Integer> {
		
		
		Sinister findByDeclarationDateBetween(Date from,Date to );
		
		
		Sinister findBySinisterDateBetween(Date from,Date to );
		
		
		@Query(value = "SELECT * FROM sinister s WHERE s.contract_contract_id= ?1 " , nativeQuery = true)
		
	    List<Sinister>findAllByContract(Contract c ) ; 
		

		
		List<Sinister> findByContract(Contract contract) ;
		
		
		
		 
		
	 
	
}
