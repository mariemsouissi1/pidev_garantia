package tn.esprit.infini2.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.infini2.entities.Provisions;

@Repository
public interface ProvisionsRepository extends CrudRepository<Provisions, Long>{

}
