package tn.esprit.infini2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.infini2.entities.Sinister;
import tn.esprit.infini2.repositories.SinisterRepository;
@Service
public class SinisterServicelmpl  implements ISinisterService{
	@Autowired
	SinisterRepository sinisterRepository;
	@Override
	public List<Sinister> retrieveAllSinisters() {
		return (List<Sinister>) sinisterRepository.findAll();
	}

	@Override
	public Sinister addSinister(Sinister c) {
		sinisterRepository.save(c);
		return c;
	}

	@Override
	public void deleteSinister(Long id) {
		sinisterRepository.deleteById(id);		
	}

	@Override
	public Sinister updateSinister(Sinister u) {
		sinisterRepository.save(u);
		return u;
	}

	@Override
	public Sinister retrieveSinister(Long id) {
		return sinisterRepository.findById(id).orElse(null);

	}

}
