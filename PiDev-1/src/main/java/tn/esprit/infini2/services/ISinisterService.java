package tn.esprit.infini2.services;

import java.util.List;

import tn.esprit.infini2.entities.Sinister;

public interface ISinisterService {
	List<Sinister> retrieveAllSinisters();

	Sinister addSinister(Sinister c);

	void deleteSinister(Long id);

	Sinister updateSinister(Sinister u);

	Sinister retrieveSinister(Long id);
}
