package tn.esprit.infini2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.infini2.entities.Credit;
import tn.esprit.infini2.repositories.CreditRepository;

@Service
public class CreditServicelmpl implements ICreditService{
	@Autowired
	CreditRepository creditRepository;

	@Override
	public List<Credit> retrieveAllCredit() {
		return (List<Credit>) creditRepository.findAll();
	}

	@Override
	public Credit addCredit(Credit c) {
		creditRepository.save(c);
		return c;
	}

	@Override
	public void deleteCredit(Long id) {
		creditRepository.deleteById(id);		
	}

	@Override
	public Credit updateCredit(Credit u) {
		creditRepository.save(u);
		return u;
	}

	@Override
	public Credit retrieveCredit(Long id) {
		return creditRepository.findById(id).orElse(null);
	}

}
