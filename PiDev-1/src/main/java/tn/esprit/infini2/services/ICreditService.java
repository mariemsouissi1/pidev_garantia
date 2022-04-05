package tn.esprit.infini2.services;

import java.util.List;

import tn.esprit.infini2.entities.Credit;

public interface ICreditService {
	List<Credit> retrieveAllCredit();

	Credit addCredit(Credit c);

	void deleteCredit(Long id);

	Credit updateCredit(Credit u);

	Credit retrieveCredit(Long id);
}
