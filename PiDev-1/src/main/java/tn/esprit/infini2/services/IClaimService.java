package tn.esprit.infini2.services;

import java.util.List;

import tn.esprit.infini2.entities.Claim;

public interface IClaimService {
	List<Claim> retrieveAllClaims();

	Claim addClaim(Claim c);

	void deleteClaim(Long id);

	Claim updateClaim(Claim u);

	Claim retrieveClaim(Long id);
}
