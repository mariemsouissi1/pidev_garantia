package tn.esprit.infini2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.infini2.entities.Claim;
import tn.esprit.infini2.entities.Contract;
import tn.esprit.infini2.repositories.ContractRepository;

public class ContractServiceImp implements IContractService{

	@Autowired
	ContractRepository ContractRepository;
	@Override
	public List<Contract> retrieveAllContracts() {
		return (List<Contract>) ContractRepository.findAll();
	}

	@Override
	public Contract addContract(Contract c) {
		ContractRepository.save(c);
		return c;
	}

	@Override
	public void deleteContract(Long id) {
		ContractRepository.deleteById(id);		
		
	}

	@Override
	public Contract updateContract(Contract u) {
		ContractRepository.save(u);
		return u;
	}

	@Override
	public Contract retrieveContract(Long id) {
		return ContractRepository.findById(id).orElse(null);
	}

}
