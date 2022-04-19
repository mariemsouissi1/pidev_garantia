package tn.esprit.infini2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.infini2.dto.GeneralScoreStat;
import tn.esprit.infini2.entities.CustomerAccount;
import tn.esprit.infini2.entities.ScoreType;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Long> {
    @Query("SELECT c FROM CustomerAccount c where c.scoreType= :scoreType")
    List<CustomerAccount> retrieveCustomers(@Param("scoreType") ScoreType scoreType);

    @Query(value = "SELECT COUNT(*) AS n , score_type as scoreTypeName FROM customer_account c GROUP BY c.score_type",nativeQuery = true)
    List<GeneralScoreStat> retrieveScoreStat();
}
