package tn.esprit.infini2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.infini2.entities.Credit;



@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {

		@Query(value="select * from credit c WHERE c.customer_credit_id_customer_account=?1 ",nativeQuery=true)
		public List<Credit> findByCustomerAccountIdAllCredits(long idCustomerAccount);
			
		@Query(value="select count(*) from credit WHERE customer_credit_id_customer_account=?1", nativeQuery=true)
		int getCountCreditPerCustomer(@Param("idCustomerAccount") long idCustomerAccount);

}
