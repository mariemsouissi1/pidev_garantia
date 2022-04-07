package tn.esprit.infini2.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import tn.esprit.infini2.entities.Contract;
import tn.esprit.infini2.entities.Premium;
import tn.esprit.infini2.entities.Sinister;
import tn.esprit.infini2.entities.SinisterStatus;
import tn.esprit.infini2.entities.Type_Contract;
import tn.esprit.infini2.entities.customer;
import tn.esprit.infini2.repositories.ContractRepository;
import tn.esprit.infini2.repositories.PremiumRepository;
import tn.esprit.infini2.repositories.SinisterRepository;

@Service
public class PremiumServicelmpl implements IPremiumService
{
		@Autowired
		PremiumRepository premiumRepository;
		@Autowired
		SinisterRepository sinisterRepository;
		@Autowired
		ContractRepository contractRepository;
		@Autowired
		ContractRepository customerRepository;
		@Autowired
		IContractService contractService;
		
		@Override
		public List<Premium> retrieveAllPremiums() {
		;	return (List<Premium>) premiumRepository.findAll();
		}
		
		@Override
		public Premium addPremium(Premium o) {
			return premiumRepository.save(o);
		}
		
		@Override
		public Premium updatePremium(Premium o) {
			premiumRepository.save(o);
			return o;
		}
		
		@Override
		public Premium retrievePremium(Long id) {
			return premiumRepository.findById(id).orElse(null);
		}
		
		@Override
		public void removePremium(Long id) {
			premiumRepository.deleteById(id);
		}
		
		@Override
		public double CalculatePPValuePrimium (LocalDate BeginDate,LocalDate endDate) {
			List<Sinister> ls= (List<Sinister>) sinisterRepository.findAll();
			
			int i=0;
			double totalCost=50;
			for(Sinister itr :ls)
			{
			
			if(itr.getSinisterDate().isAfter(BeginDate)&&itr.getSinisterDate().isBefore(endDate))
				totalCost+=itr.getSinisterIndemnity();
				i++;
			}
			
			
			int counter=0;
		List<Contract> lc= (List<Contract>) contractRepository.findAll();
			for(Contract itr :lc)
			{
				
					
			if (itr.getCreationDate().isAfter(BeginDate)&&itr.getCreationDate().isBefore(endDate))
				
				counter++;
			
			}
			
			if(counter<10)
				counter=10;
			
			
			return totalCost/counter;
		} 

		
		
		@Override
		public Double calculatePremium( String typeA , LocalDate BeginigOfYear,LocalDate endDate, customer customer, double FNG, double comission) {
		
			double PrimValue=0;
		Double PPValue = CalculatePPValuePrimium(BeginigOfYear,endDate);
	//	float Score = calculScore(customer);
		float Score = 7;
			
			if( Score <= 5) {
					switch(typeA) {
						case "Medical Insurance":
							PrimValue= PPValue * 0.0065;
						case "Property Insurance":
							PrimValue= PPValue * 0.0035;
						case "Life Insurance":
							PrimValue= PPValue * 0.0025;
						case "Vehicule Insurance" :
							PrimValue= PPValue * 0.0015;
						case "Agriculture Insurance" :
							PrimValue= PPValue * 0.0025;	
					}
					
			}else if(Score > 5 && Score <= 10) {
					switch(typeA) {
						case "medicalInsurance":
							PrimValue= PPValue * 0.75;
						case "PropertyInsurance":
							PrimValue= PPValue * 0.0065;
						case "LifeInsurance":
							PrimValue= PPValue * 0.0055;
						case "Vehicule Insurance" :
							PrimValue= PPValue * 0.0025;
						case "Agriculture Insurance" :
							PrimValue= PPValue * 0.0035;	
			}
				
			}else {
					switch(typeA) {
					case "Medical Insurance":
						PrimValue= PPValue * 0.0075;
					case "Property Insurance":
						PrimValue= PPValue * 0.0065;
					case "Life Insurance":
						PrimValue= PPValue * 0.0055;	
					case "Vehicule Insurance" :
						PrimValue= PPValue * 0.0045;	
					case "Agriculture Insurance" :
						PrimValue= PPValue * 0.0035;	
				
					}
			
			}
			double totalPrimium=0;
			totalPrimium = PrimValue + comission + FNG;
			return totalPrimium;
			
			
			
		}
		
		@Override
        public HashMap<Long, Double> CalculateDiscount(Long id_customer){
			HashMap<Long,Double> results_map = new HashMap<Long,Double>();    	
    		List<Contract> lc= contractService.findCustomerContracts(id_customer);
    		for(Contract itr :lc )
    		{
    			if(itr.getCreationDate()== itr.getC_customerAccount().getCustomer().getBirthDate())
    			//if (true)
    			{
        		results_map.put(itr.getIdContract(),  Double.valueOf(itr.getPrimeContract()*0.95));
    			itr.setPrimeContract(itr.getPrimeContract()*0.95);
    			 contractRepository.save(itr);
    				
    			}
    			
    		    if (Period.between(itr.getCreationDate(), LocalDate.now()).getYears()>=5) {
    		    	results_map.put(itr.getIdContract(), Double.valueOf(itr.getPrimeContract()*0.95));
    		    	itr.setPrimeContract(itr.getPrimeContract()*0.95);
    		    	 contractRepository.save(itr);
    		    }

    			
    		}
    		return results_map;
    	
    		
       		

    	}
		
		
	


	
}
