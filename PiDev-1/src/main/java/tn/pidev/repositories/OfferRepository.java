package tn.pidev.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.pidev.entities.Offer;

@Repository
public interface OfferRepository extends CrudRepository<Offer, Long> {

}
