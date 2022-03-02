package tn.esprit.infini2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.infini2.entities.Claim;
import tn.esprit.infini2.repositories.ClaimRepository;
@Service
public class ClaimServicelmpl  implements IClaimService{
	@Autowired
	ClaimRepository claimRepository;
	@Override
	public List<Claim> retrieveAllClaims() {
		return (List<Claim>) claimRepository.findAll();
	}

	@Override
	public Claim addClaim(Claim c) {
		claimRepository.save(c);
		return c;
	}

	@Override
	public void deleteClaim(Long id) {
		claimRepository.deleteById(id);		
	}

	@Override
	public Claim updateClaim(Claim u) {
		claimRepository.save(u);
		return u;
	}

	@Override
	public Claim retrieveClaim(Long id) {
		return claimRepository.findById(id).orElse(null);

	}

}
