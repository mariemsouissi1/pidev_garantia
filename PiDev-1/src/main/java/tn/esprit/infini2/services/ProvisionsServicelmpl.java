package tn.esprit.infini2.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.infini2.entities.Provisions;
import tn.esprit.infini2.entities.Sinister;
import tn.esprit.infini2.entities.SinisterStatus;

import tn.esprit.infini2.repositories.ProvisionsRepository;
import tn.esprit.infini2.repositories.SinisterRepository;



@Service
public class ProvisionsServicelmpl implements IProvisionsService
{
		@Autowired
		ProvisionsRepository provisionsRepository;
		@Autowired
		SinisterRepository sinisterRepository;
		
		@Override
		public List<Provisions> retrieveAllProvisionss() {
		;	return (List<Provisions>) provisionsRepository.findAll();
		}
		
		@Override
		public Provisions addProvisions(Provisions o) {
			return provisionsRepository.save(o);
		}
		
		@Override
		public Provisions updateProvisions(Provisions o) {
			provisionsRepository.save(o);
			return o;
		}
		
		@Override
		public Provisions retrieveProvisions(Long id) {
			return provisionsRepository.findById(id).orElse(null);
		}
		
		@Override
		public void removeProvisions(Long id) {
			provisionsRepository.deleteById(id);
		}

		@Override
		public List<Provisions> retrieveAllProvisions() {
			// TODO Auto-generated method stub
			return null;
		}
		
    
		@Override
        public double CalculateProvisionsConnues(){
    		List<Sinister> ls= (List<Sinister>) sinisterRepository.findAll();
    		
    		double sum=0;
    		for(Sinister itr :ls)
    		{
    		
    		if( itr.getSinisterStatus()==SinisterStatus.IN_PROCESS )
    			sum= sum + itr.getSinisterIndemnity();
    			
    		}
    	
    		return sum;
    	}
		
		public double CalculateProvisionsNonConnues(LocalDate BeginigOfYear, LocalDate endDate){
			List<Sinister> ls=(List<Sinister>) sinisterRepository.findAll();
			//Logger.getGlobal().log(Level.INFO, "test");
			double ProvNC=50;
			//Logger.getGlobal().log(Level.INFO, "BeginigOfYear: "+BeginigOfYear.toString()+ " endDate: " + endDate.toString());
			for(Sinister itr :ls)
			{
				//Logger.getGlobal().log(Level.INFO, "in Sinister for Loop getSinisterDate: "+itr.getSinisterDate().toString());
			
			//if((itr.getSinisterDate().isAfter(BeginigOfYear))&&(itr.getSinisterDate().isBefore(endDate))&&(itr.getSinisterStatus()==SinisterStatus.RESOLVED))
			

				{ProvNC= ProvNC+itr.getSinisterIndemnity();}
		
	
			}
		
			
			return ProvNC;
		} 

		
		

}
