package tn.esprit.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.entities.Offer;

@Repository
public interface OfferRepository extends CrudRepository<Offer, Long> {

}
