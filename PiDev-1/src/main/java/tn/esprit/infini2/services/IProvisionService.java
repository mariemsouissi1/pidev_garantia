package tn.esprit.infini2.services;

import java.util.List;

import tn.esprit.infini2.entities.Provisions;

public interface IProvisionService {
	List<Provisions> retrieveAllProvisions();

	Provisions addProvisions(Provisions c);

	void deleteProvisions(Long id);

	Provisions updateProvisions(Provisions u);

	Provisions retrieveProvisions(Long id);
}
