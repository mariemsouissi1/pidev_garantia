package tn.esprit.infini2.services;

import java.util.List;

import tn.esprit.infini2.entities.Contract;

public interface IContractService {
	List<Contract> retrieveAllContracts();

	Contract addContract(Contract c);

	void deleteContract(Long idContract);

	Contract updateContract(Contract u);

	Contract retrieveContract(Long idContract);
}
