package tn.esprit.infini2.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.infini2.entities.Contract;
import tn.esprit.infini2.entities.Customer;
import tn.esprit.infini2.entities.Employee;
import tn.esprit.infini2.entities.Sinister;
import tn.esprit.infini2.entities.SinisterStatus;

import tn.esprit.infini2.repositories.ContractRepository;
import tn.esprit.infini2.repositories.CustomerRepository;
import tn.esprit.infini2.repositories.EmployeeRepository;
import tn.esprit.infini2.repositories.SinisterRepository;

@Service
public class SinisterServiceImpl  implements ISinisterService{
	
	
	@Autowired
	SinisterRepository sinisterRepository;
	@Autowired
	EmployeeRepository employeeRep ;
	
	@Autowired
	ContractRepository contractRep ;
	
	@Autowired
	CustomerRepository customerRep ;
	@Autowired
	EmailService emailservice;
	
	
	
	private static final org.apache.logging.log4j.Logger l = LogManager.getLogger(SinisterServiceImpl.class);

	
	
	

/////////////////////////////////////////////////////////////////////////etrieveAllSinisters//////////////////////////////////////////////////////////////:
	@Override
	public List<Sinister> retrieveAllSinisters() {
		
List<Sinister> sinisters = (List<Sinister>) sinisterRepository.findAll();
		
		for (Sinister sinister : sinisters){
			
			l.info("sinister++:" + sinister) ;
	}
		return sinisters;
	}
	
	
	
	
	
	
	

/////////////////////////////////////////////////////////////////////////ajouterSinister//////////////////////////////////////////////////////////////:
	@Override
	public Sinister addSinister(Sinister sinister) {
		
		sinister.setDeclarationDate(Calendar.getInstance().getTime());
		
		sinister.setDelaiDeclaration(2);
		
		sinister.setSinisterStatus(SinisterStatus.IN_PROCESS);
		
		sinisterRepository.save(sinister);
		
		System.out.println(sinister.getIdSinister()) ;
		
		return sinister;
	
		
	}
	
	
	
/////////////////////////////////////////////////////////////////////////deleteSinister//////////////////////////////////////////////////////////////:

	@Override
	public String deleteSinister(Integer idSinister) {
		
		sinisterRepository.deleteById( idSinister);
		
		return "Sinister removed !! " + idSinister;
	}
	
	
	
/////////////////////////////////////////////////////////////////////////suivreSinistre//////////////////////////////////////////////////////////////:
	@Override
public String suivreSinistre(Integer sinisterId) {
		
		Sinister sinister = sinisterRepository.findById(sinisterId).orElse(null);
		
		
		if(sinister.getSinisterStatus().equals(SinisterStatus.REJECTED)){
			
			return "your claim declared on: "+sinister.getDeclarationDate()
			
			+"is rejected,"+"\n"+"\r\n"
			
			+ "reason for rejection:"+sinister.getCauseRejet();
			
		}else
		
		return "your claim is being processed";
	}
	


	
	
/////////////////////////////////////////////////////////////////////////retrieveSinister//////////////////////////////////////////////////////////////:	
	@Override
	public Sinister retrieveSinister(Integer idSinister) {
		
		return sinisterRepository.findById(idSinister).orElse(null);
	}

	
	
	
/////////////////////////////////////////////////////////////////////////retrieveSinisterByEmployee//////////////////////////////////////////////////////////////:

	@Override
	public List<Sinister> retrieveSinisterByEmployee(Long idEmployee) {
		
		Employee em = employeeRep.findById(idEmployee).orElse(null) ;
		
	/*	List <Contract> contracts = contractRep.findAllByEmployee(em);
		
		for (Contract contract : contracts){     
		
		List<Sinister> sinisters = (List<Sinister>) sinisterRepository.findAllByContract(contract);
		
		for (Sinister sinister : sinisters){
			
			l.info("sinister++:" + sinister) ;
			
			System.out.println(sinister) ;
			

		}return sinisters ;
			
		}*/return null ;
		}
	
	
	
	
	
	
/////////////////////////////////////////////////////////////////////////retrieveSinisterByCustomer//////////////////////////////////////////////////////////////:
	@Override
	public List<Sinister> retrieveSinisterByCustomer(Long idCustomer) {
		
		Customer cu = customerRep.findById(idCustomer).orElse(null) ;
		/*
		List <Contract> contracts = contractRep.findAllByCustomer(cu);
		
		for (Contract contract : contracts){     
		
		List<Sinister> sinisters = (List<Sinister>) sinisterRepository.findAllByContract(contract);
		
		for (Sinister sinister : sinisters){
			
			l.info("sinister++:" + sinister) ;
			
			System.out.println(sinister) ;

		}return sinisters ;
			
		}*/return null ;
		}
	

/////////////////////////////////////////////////////////////////////////retrieveSinisterByDeclarationDate//////////////////////////////////////////////////////////////:
	@Override
	public List<Sinister> retrieveSinisterByDeclarationDate(Date from, Date to) {
		
		
		 return sinisterRepository.findByDeclarationDateBetween(from, to);
	}
	
	
/////////////////////////////////////////////////////////////////////////retrieveSinisterBySinisterDate//////////////////////////////////////////////////////////////:
	@Override
	public List<Sinister> retrieveSinisterBySinisterDate(Date from, Date to) {
		

		return sinisterRepository.findBySinisterDateBetween(from, to);
	}

	
	
	
	
/////////////////////////////////////////////////////////////////////////verificationSinisterDelay//////////////////////////////////////////////////////////////:
	@Override
	public String verificationSinisterDelay(Integer idSinister) {
		
		double nbrDays  =0;
		
		String message = null ; 
		
        Sinister sinister = sinisterRepository.findById(idSinister).orElse(null)	;
        
        Date a = sinister.getDeclarationDate() ;
        
        int b =sinister.getDelaiDeclaration();
        
        Date c = sinister.getSinisterDate() ;
        
        
        long diff = a.getTime() - c.getTime();
        
        nbrDays = (diff / (1000*60*60*24));
        
        
        if(nbrDays <= b){
        	System.out.println("deadline well verificationed, deadline respected!") ;
        	
        	message = "deadline well verificationed, deadline respected!" ;
        	}
        
        else {
        	System.out.println("time expired, you have no right to compensation, claim rejected!!");
        	
        	message = "time expired, you have no right to compensation, claim rejected!! ";
        	
        	}
        return message ;
	}
	


	
/////////////////////////////////////////////////////////////////////////traiterSinistre//////////////////////////////////////////////////////////////
	@Override
	public String traiterSinister( Integer idSinister) throws Exception {
		
		String message = null ; 
		
		Sinister s = sinisterRepository.findById(idSinister).orElse(null);
		
		/*if(verificationSinisterDelay(idSinister).contains("deadline well verificationed, deadline respected!")){
			
			System.out.println(verificationSinisterDelay(idSinister).toString()) ;
			
			Contract c=contractRep.retrieveContract( idSinister);
			
			if(s.getChargeSinister()<=c.getPlafond()){
				
				Date d =Calendar.getInstance().getTime();
				
				s.setIndemnisationDate(d);
				
				s.setSinisterIndemnity(s.getChargeSinister());
				
			  	message = "your benefit for the claim declared on:"
			  			
				+s.getDeclarationDate()+"is done"+"\n"+"compensation accepted amountCompensated:"
				
			  	+s.getSinisterIndemnity()+"Indemnity date"+"\n"
				
				+s.getIndemnisationDate();
			  	
				s.setSinisterStatus(SinisterStatus.RESOLVED);
				
				sinisterRepository.save(s);
				}
			else if ( s.getChargeSinister() > c.getPlafond() ) {
				
				Date d =Calendar.getInstance().getTime();
				
				s.setIndemnisationDate(d);
				
				s.setSinisterIndemnity(c.getPlafond());
				
			  	message = "your benefits for the claim declared on :"
			  			
				+s.getDeclarationDate()+"is done"+"\n"+"compensation accepted in the amountCompensated:"
				
			  	+s.getSinisterIndemnity()+"Indemnity date"+"\n"
			  	
				+s.getIndemnisationDate();
			  	
				s.setSinisterStatus(SinisterStatus.RESOLVED);
				
				sinisterRepository.save(s); 				
				}		
				
				System.out.println(retrieveSinister(idSinister)); 
			}
		
		else{ 
			message = "compensation denied" ;
			
		    System.out.println("compensation denied") ;
		    
		    s.setSinisterStatus(SinisterStatus.REJECTED);
		    
		    s.setCauseRejet("deadline or instructions not respected");
		    
		    sinisterRepository.save(s);
		    
		    System.out.println(verificationSinisterDelay(idSinister).toString()) ;
		    }*/
		return message+"\n"+s;
	}


	/////////////////////////////////////////////////////////////////////////mettre a jour sinsitre avec des conditios//////////////////////////////////////////////////////////////:

	@Override
	public String updateSinister(Sinister sinister, Integer idSinister) {
		
		
		Sinister existingSinister = sinisterRepository.findById(idSinister).orElse(null);
		
		if(sinister.getDeclarationDate()!=null){
			
		existingSinister.setDeclarationDate(sinister.getDeclarationDate());	
		}
		
		if(sinister.getSinisterDate()!=null){

		existingSinister.setSinisterDate(sinister.getSinisterDate());
		}
		
		if(sinister.getSinisterDescription()!=null){
		
		existingSinister.setSinisterDescription(sinister.getSinisterDescription());
		}
		
		if(sinister.getSinisterPlace()!=null){

		existingSinister.setSinisterPlace(sinister.getSinisterPlace());
		}
		
		if(sinister.getSinisterStatus()!=null){

		existingSinister.setSinisterStatus(existingSinister.getSinisterStatus());
		}
		
		if(sinister.getSinisterType()!=null){

		existingSinister.setSinisterType(sinister.getSinisterType());
		}
		
		
		if(sinister.getDelaiDeclaration()!=0){

		existingSinister.setDelaiDeclaration(sinister.getDelaiDeclaration());
		}
		
		 sinisterRepository.save(existingSinister);
		
		
	 return "update completed successfully\\n"+existingSinister ;

	
	}
	
	
/////////////////////////////////////////////////////////////////////////CalculRapiditéReglementSinistre//////////////////////////////////////////////////////////////:
	@Override
	public String CalculRapiditéReglementSinistre(Integer idSinister) {
		
		double rapiditéReglementSinistre =0;
		
		
		Sinister s= sinisterRepository.findById(idSinister).orElse(null);
		
	Date	a=s.getDeclarationDate();
	
	Date b = s.getIndemnisationDate();
	
	long diff = b.getTime() - a.getTime();
	
	rapiditéReglementSinistre = (diff / (1000*60*60*24));
	
	System.out.println("Number of days between the two dates is: "
			
			+ ""+rapiditéReglementSinistre+"\r\n"
			
			+ "days");
	
	String chaine = rapiditéReglementSinistre +"\r\n"
			+ "days" ;
	
		return  chaine ;
	}
	






	/*@Override
	public void affectContractToSinister(Integer idSinister, Long idContrat) {
		// TODO Auto-generated method stub
		
		Sinister sinisters = new Sinister();
		sinisters = contractRep.findById(idSinister).get();
		sinisters.setFkContract(contractRep.findById(idContrat).get());
		sinisters.save(sinisters);
		}*/
		
	}
