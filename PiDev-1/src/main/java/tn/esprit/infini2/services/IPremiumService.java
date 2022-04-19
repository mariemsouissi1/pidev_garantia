package tn.esprit.infini2.services;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import tn.esprit.infini2.entities.Premium;
import tn.esprit.infini2.entities.Customer;

public interface IPremiumService {
	List<Premium> retrieveAllPremiums();

	Premium addPremium(Premium p);

	void removePremium(Long idPremium);

	Premium updatePremium(Premium p);

	Premium retrievePremium(Long idPremium);

	Double calculatePremium( String typeA, LocalDate BeginigOfYear,LocalDate endDate, Customer customer,double FNG, double comission);

	double CalculatePPValuePrimium(LocalDate BeginDate, LocalDate endDate);

	HashMap<Long, Double> CalculateDiscount(Long id_customer);

	
}
