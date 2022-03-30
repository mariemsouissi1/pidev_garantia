package tn.esprit.infini2.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.infini2.entities.Contract;
import tn.esprit.infini2.entities.Sinister;
import tn.esprit.infini2.repositories.Indemnity_payRepository;
import tn.esprit.infini2.repositories.SinisterRepository;

@Transactional
@Service
public class SinisetrServiceImpl implements ISinisterService{
	 
		@Autowired
	    public JavaMailSender emailSender;
		
		@Autowired
		SinisterRepository SinisterRepository;
		
		
		
		
		@Autowired
		employeeRepository employeeRep ;
		
		@Autowired
		ContractRepository contractRep ;
		
		@Autowired
		CustomerRepository customerRep ;
		
		@Autowired
		Indemnity_payRepository Indemnity_payRep ;
		
		@Autowired
		Iindemnity_payService Iindemnity_payService ;
		
		
		
		private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(UserServiceImpl.class);

		@Override
		public Sinister addSinister(Sinister sinister) {
			// TODO Auto-generated method stub
			sinister.setDeclarationDate(Calendar.getInstance().getTime());
			sinister.setDelaiDeclaration(7);
			sinister.setSinisterStatus("en cours");
			sinisterRep.save(sinister);
			System.out.println(sinister.getId()) ;
			
			return sinister;
		
		}

		@Override
		public String deleteSinister(int id) {
			// TODO Auto-generated method stub
			SinisterRepository.deleteById( id);
			return "the claim has been successfully deleted " + id;
			
			
		}

		@Override
		public String updateSinister(Sinister sinister, int idSinister) {
			// TODO Auto-generated method stub
			
			Sinister sinisterfound = SinisterRepository.findById(idSinister).orElse(null);
			
				if(sinister.getSinisterPlace()!=null){

				sinisterfound.setSinisterPlace(sinister.getSinisterPlace());
				}
				if(sinister.getSinisterStatus()!=null){

				sinisterfound.setSinisterStatus(sinisterfound.getSinisterStatus());
				}
				if(sinister.getSinisterType()!=null){

				sinisterfound.setSinisterType(sinister.getSinisterType());
				}
				if(sinister.getContract()!=null){

				sinisterfound.setContract(sinister.getContract());
				}
				if(sinister.getDelaiDeclaration()!=0){

				sinisterfound.setDelaiDeclaration(sinister.getDelaiDeclaration());
				}
				if(sinister.getDeclarationDate()!=null){
					sinisterfound.setDeclarationDate(sinister.getDeclarationDate());	
					}
					if(sinister.getSinisterDate()!=null){

					sinisterfound.setSinisterDate(sinister.getSinisterDate());
					}
					if(sinister.getSinisterDescription()!=null){
					
					sinisterfound.setSinisterDescription(sinister.getSinisterDescription());
					}
				
				
				 SinisterRepository.save(sinisterfound);
				 
				 return "update completed successfully\n"+sinisterfound ;
				
			
		}
		
		
		//afficher sinistre by id bycontract by declaration date ...
		
		

		@Override
		public Sinister retrieveSinister(int id) {
			// afficher by id
			return  SinisterRepository.findById(id).orElse(null);
		}
			

		@Override
		public List<Sinister> retrieveSinisterByContract(int contractId) {
			// TODO Auto-generated method stub
			Contract c = contractRep.findById(contractId).orElse(null) ;
			
			List<Sinister> sinisters = SinisterRepository.findAllByContract(c);
			return sinisters ;
			
		}

		@Override
		public Sinister retrieveSinisterByDeclarationDate(Date from, Date to) {
			// TODO Auto-generated method stub
			return SinisterRepository.findByDeclarationDateBetween(from, to);
			
		}

		@Override
		public Sinister retrieveSinisterBySinisterDate(Date from, Date to) {
			// TODO Auto-generated method stub
			return SinisterRepository.findBySinisterDateBetween(from, to);
			
		}

		@Override
		public Sinister declarerSinistre(Sinister sinister, int contractId) {
			// TODO Auto-generated method stub
			//, int customerId 
			
			//Insured insd = insuredRep.findById(insuredId).orElse(null);
			
			//List <Contract> contracts =insd.getContracts();
			//for (Contract c : contracts){
				
			//}
			
			
			Contract contract = contractRep.findById(contractId).orElse(null) ;
			//int idInsurer = contract.getInsurer().getId() ;
			//contract.getInsured().getId() ;
			
				System.out.println("successful claim notification") ;
			sinister.setSinisterStatus("in process");
			sinister.setDeclarationDate(Calendar.getInstance().getTime());
			sinister.setIndemnisationDate(null);
			sinister.setCauseRejet(null);
			sinister.setDelaiDeclaration(7);
			sinister.setChargeSinister(0);
			sinister.setContract(contract);
			 //sinister.setSinisterType(contract.getContractType()) ;
		SinisterRepository.save(sinister);
			//envoyerMailToInsurer(idInsurer);
			
			
			
			return sinister ;
		}

		@Override
		public Sinister affecterContratSinister(int idContrat, int idSinister) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String traiterSinistre(int siniterId)  {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String CalculRapiditéReglementSinistre(int sinisterId) {
			// TODO Auto-generated method stub
			return null;
		}

		
		@Override
		public String CalculRatioRejetDemIndemn(int sinisterId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String CalculCoutMoyen(int employeeId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String verifierDelai(int sinisterId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String envoyerMailToEmployee(int employeeId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String envoyerMailToCustomer(int customerId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String suivreSinistre(int sinisterId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String calculSinistrWallet(int employeeId) {
			// TODO Auto-generated method stub
			return null;
		}

		
		@Override
		public double verifierFacture(int sinisterId) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public String payerSinistre(int sinisterId) {
			// TODO Auto-generated method stub
			return null;
		}
		
		
		
		private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(UserServiceImpl.class);

		@Override
		public List<Sinister> retrieveAllSinisters() {
			// TODO Auto-generated method stub
			List<Sinister> sinisters = (List<Sinister>) SinisterRepository.findAll();
			
			for (Sinister sinister : sinisters){
				logger.info("sinister++:" + sinister) ;
		}
			return sinisters;
			
		}

		@Override
		public List<Sinister> retrieveSinisterByEmployee(int employeeId) {
			// TODO Auto-generated method stub
			Employee in = EmployeeRep.findById(employeeId).orElse(null) ;
			
			List <Contract> contracts = contractRep.findAllBycustomer(in);
			
			for (Contract contract : contracts){     
			
			List<Sinister> sinisters = (List<Sinister>)SinisterRepository.findAllByContract(contract);
			for (Sinister sinister : sinisters){
				logger.info("sinister++:" + sinister) ;
				System.out.println(sinister) ;
			}return sinisters ;
			
			}return null ;
			}

		@Override
		public List<Sinister> retrieveSinisterByCustomer(int customerId) {
			// TODO Auto-generated method stub
			Customer in = customerRep.findById(customerId).orElse(null) ;
			
			List <Contract> contracts = contractRep.findAllByInsured(in);
			
			for (Contract contract : contracts){     
			
			List<Sinister> sinisters = (List<Sinister>) SinisterRepository.findAllByContract(contract);
			for (Sinister sinister : sinisters){
				logger.info("sinister++:" + sinister) ;
				System.out.println(sinister) ;

			}return sinisters ;
				
			}return null ;
		    }
}

