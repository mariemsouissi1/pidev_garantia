package tn.esprit.infini2.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.infini2.entities.Provisions;
import tn.esprit.infini2.entities.Sinister;

@Repository
public interface SinisterRepository extends CrudRepository<Sinister, Long>{

}
