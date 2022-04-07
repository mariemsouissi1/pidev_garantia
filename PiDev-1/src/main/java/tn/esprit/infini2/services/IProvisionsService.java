package tn.esprit.infini2.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import tn.esprit.infini2.entities.Provisions;

public interface IProvisionsService {
	
	List<Provisions> retrieveAllProvisions();

	Provisions addProvisions(Provisions pr);

	void removeProvisions(Long idProvisions);

	Provisions updateProvisions(Provisions pr);

	Provisions retrieveProvisions(Long idProvisions);

	List<Provisions> retrieveAllProvisionss();

	double CalculateProvisionsConnues();

	double CalculateProvisionsNonConnues(LocalDate localDate, LocalDate localDate2);

}
