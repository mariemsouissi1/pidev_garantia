package tn.esprit.infini2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import tn.esprit.infini2.entities.Premium;
import tn.esprit.infini2.repositories.PremiumRepository;

@Service
public class PremiumServicelmpl implements IPremiumService
{
		@Autowired
		PremiumRepository premiumRepository;
		
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
		public Double calculatePremium(double PPValue, String typeA , long salary) {
			
			if( salary <= 450) {
					switch(typeA) {
						case "Medical Insurance":
							return PPValue * 0.0065;
						case "Property Insurance":
							return PPValue * 0.0035;
						case "Life Insurance":
							return PPValue * 0.0025;
						case "Vehicule Insurance" :
							return PPValue * 0.0015;
						case "Agriculture Insurance" :
							return PPValue * 0.0025;	
					}
					
			}else if(salary > 450 && salary <= 800) {
					switch(typeA) {
						case "Medical Insurance":
							return PPValue * 0.0075;
						case "Property Insurance":
							return PPValue * 0.0065;
						case "Life Insurance":
							return PPValue * 0.0055;
						case "Vehicule Insurance" :
							return PPValue * 0.0025;
						case "Agriculture Insurance" :
							return PPValue * 0.0035;	
			}
				
			}else {
					switch(typeA) {
					case "Medical Insurance":
						return PPValue * 0.0075;
					case "Property Insurance":
						return PPValue * 0.0065;
					case "Life Insurance":
						return PPValue * 0.0055;	
					case "Vehicule Insurance" :
						return PPValue * 0.0045;	
					case "Agriculture Insurance" :
						return PPValue * 0.0035;	
				
					}
			
			}
			return 0.0;
		}

	
}
