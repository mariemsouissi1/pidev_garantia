package tn.esprit.infini2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.infini2.entities.Claim;

@Repository
public interface ClaimRepository extends CrudRepository<Claim, Long>{
	@Query(value="SELECT * FROM Claim C WHERE C.claim_visibility=:test",nativeQuery = true)
			public List<Claim> Retrievevisibility(@Param("test")Boolean claim_visibility);
	
	@Query(value="SELECT count(*) FROM Claim C WHERE C.claim_visibility=:test",nativeQuery = true)
	public int Nbclaimselonvisibility(@Param("test")Boolean claim_visibility);
	
	@Query(value="SELECT count(*) FROM Claim C",nativeQuery = true)
	public int Nbclaims_total();
	
	@Query(value="SELECT * FROM Claim C WHERE C.customer_claim_id_customer_account=:id AND C.claim_visibility=:true ",nativeQuery = true)
	public List<Claim> Retrieve_claim_par_idclient(@Param("id")Long idcustomer);
}
