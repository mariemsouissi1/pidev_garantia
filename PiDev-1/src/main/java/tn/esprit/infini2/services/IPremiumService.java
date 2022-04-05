package tn.esprit.infini2.services;

import java.util.List;

import tn.esprit.infini2.entities.Premium;

public interface IPremiumService {
	List<Premium> retrieveAllPremiums();

	Premium addPremium(Premium c);

	void deletePremium(Long id);

	Premium updatePremium(Premium u);

	Premium retrievePremium(Long id);
}
