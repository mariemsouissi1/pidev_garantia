package tn.esprit.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.entities.TransactionCredit;


@Repository
public interface TransactionCreditRepository extends JpaRepository<TransactionCredit, Long> {
	
}
