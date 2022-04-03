package tn.esprit.infini2.repositories;

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
public interface CustomerAccountRepository extends CrudRepository<CustomerAccount, Long>{
    @Query("SELECT c FROM CustomerAccount c where c.scoreType= :scoreType")
    List<CustomerAccount> retrieveCustomers(@Param("scoreType") ScoreType scoreType);

   // @Query("SELECT COUNT(*) AS n, scoreType FROM customerAccount c GROUP BY c.scoreType")
    List<GeneralScoreStat> retrieveScoreStat();
}
