package tn.esprit.infini2.repositories;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.infini2.entities.Contract;
import tn.esprit.infini2.entities.Sinister;


@Repository
public interface SinisterRepository extends CrudRepository<Sinister, Integer>{

	@Query("SELECT s FROM Sinister s WHERE s.declarationDate between :from and :to ")
	List<Sinister> findByDeclarationDateBetween(@Param("from") Date from, @Param("to") Date to);
	
	@Query("SELECT s FROM Sinister s WHERE s.sinisterDate between :from and :to ")
	List<Sinister> findBySinisterDateBetween(@Param("from") Date from, @Param("to") Date to);
	
	
	@Query(value = "SELECT * FROM sinister s WHERE s.contract_id= ?1 " , nativeQuery = true)
    List<Sinister>findAllByContract(Contract c ) ; 
	

	
	
	
	 
}
		
		
		 
		
	 
	

