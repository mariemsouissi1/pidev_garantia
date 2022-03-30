package tn.esprit.infini2.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.infini2.entities.Indemnity_pay;
import tn.esprit.infini2.repositories.Indemnity_payRepository;

@Transactional
@Service

public class Indemnity_payServiceImpl implements Iindemnity_payService {
	

	@Autowired
	Indemnity_payRepository Indemnity_payRep ;
	
	@Autowired
	ContractRepository contractRep ;
	
	
	
	
	

	@Override
	public List<Indemnity_pay> retrieveAllIndemnity_pay() {
		List<Indemnity_pay> Indemnity_pay = (List<Indemnity_pay>) Indemnity_payRep.findAll();
		for (Indemnity_pay Indemnity_pay1 : Indemnity_pay){
			l.info("Indemnity_pays++:" + Indemnity_pay) ;
	}
		return Indemnity_pay;
	}

	
	
	
	
	
	
	
	@Override
	public List<Indemnity_pay> retrieveAllIndemnity_pay(int contractId) {
		// TODO Auto-generated method stub
		Contract contract = contractRep.findById(contractId).orElse(null);
		
		return  Indemnity_payRep.findAllByContract(contract);
	}

	
	
	
	
	
	
	@Override
	public Indemnity_pay addIndemnity_pay(Indemnity_pay Ip) {
		
		// 
		Ip.setIndemnity_payCode("azerty");
		
		Ip.setIndemnity_payMethod("par virement");
		
		Ip.save(Ip);
		
		System.out.println(Ip.getId()) ;
		
		return Ip;
	}
	
	

	@Override
	public void deleteIndemnity_pay(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Indemnity_pay updateIndemnity_pay(Indemnity_pay p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Indemnity_pay retrieveIndemnity_pay(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
