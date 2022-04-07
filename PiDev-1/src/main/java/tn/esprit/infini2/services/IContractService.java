package tn.esprit.infini2.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import tn.esprit.infini2.entities.Contract;
import tn.esprit.infini2.entities.Type_Contract;

public interface IContractService {
	List<Contract> retrieveAllContracts();

	Contract addContract(Contract c);

	void removeContract(Long idContract);

	Contract updateContract(Contract c);

	Contract retrieveContract(Long id);

	Contract addContract(Contract c, Long idPremium);
	
	int countContractByType(Type_Contract type);
	
	List <Contract> findContractByType(Type_Contract type);
	Contract findContractByID(Long idContract);
	void export(HttpServletResponse response,  HashMap<String,Object>contractInfo) throws IOException;

	int CountContractsBetween(String EndDate_ddmmyyyy, String BiginingDate_yymmdd);

	HashMap<Type_Contract, Object> viewContractsByTypes();

	List<Contract> findCustomerContracts(long ind);

	
}
