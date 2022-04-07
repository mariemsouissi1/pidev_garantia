package tn.esprit.infini2.services;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import tn.esprit.infini2.entities.Contract;

import tn.esprit.infini2.entities.Premium;
import tn.esprit.infini2.entities.Sinister;
import tn.esprit.infini2.entities.Type_Contract;
import tn.esprit.infini2.entities.customer;
import tn.esprit.infini2.repositories.ContractRepository;
import tn.esprit.infini2.repositories.PremiumRepository;
import tn.esprit.infini2.repositories.SinisterRepository;

@Service
public class ContractServicelmpl implements IContractService
{
		@Autowired
		ContractRepository contractRepository;
		@Autowired
		PremiumRepository premiumRepository;
		
		
		
		@Override
		public List<Contract> retrieveAllContracts() {
		;	return (List<Contract>) contractRepository.findAll();
		}
		
		@Override
		public Contract addContract(Contract c,  Long idPremium) {
			Premium p= premiumRepository.findById(idPremium).orElse(null);
			c.setPremium(p);
			//Date dt = new Date();
			LocalDate lt = LocalDate.now();
			c.setCreationDate(lt);
			contractRepository.save(c);
		    c.setExpirationDate(lt.plusDays(c.getDurationContract()));
		   
			contractRepository.save(c);
			return c;
		}
		@Override
		public Contract addContract(Contract o) {
			return contractRepository.save(o);
		}
		
		@Override
		public Contract updateContract(Contract o) {
			contractRepository.save(o);
			return o;
		}
		
		@Override
		public Contract retrieveContract(Long id) {
			return contractRepository.findById(id).orElse(null);
		}
		
		@Override
		public void removeContract(Long id) {
			contractRepository.deleteById(id);
		}

		@Override
		public int countContractByType(Type_Contract type) {
			List<Contract> listcontractbytype = contractRepository.countContractByType(type);
			int countcontractbytype = listcontractbytype.size();
			return countcontractbytype;
		}

		@Override
		public List<Contract> findContractByType(Type_Contract type) {
			List<Contract> listcontactbytype = contractRepository.findContractByType(type);
			return listcontactbytype;
		}
		@Override
		public Contract findContractByID(Long idContract) {
			System.out.println("idContract= "+ idContract);
			Contract contactbyID = contractRepository.findContractByID(idContract);
			return contactbyID;
		}
		
		@Override
	    public void export(HttpServletResponse response, HashMap<String, Object> contractInfo) throws IOException {
	        Document document = new Document(PageSize.A4);
	        PdfWriter.getInstance(document, response.getOutputStream());

	        document.open();
	        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	        fontTitle.setSize(18);

	        Paragraph paragraph = new Paragraph("Contrat d'Assurance GARANTIA", fontTitle);
	        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

	        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
	        fontParagraph.setSize(12);
	        Contract contract = (Contract) contractInfo.get("contract");
	        customer _customer = (customer) contractInfo.get("customer");
	        String content = "Nous soussignés, compagnie d’assurance GARANTIA d’une part et Mr/Mme" + 
	        _customer.getFirstName() +" "+ _customer.getLastName() + " d’autrepart, " + "ayant le numéro de CIN : " + _customer.getCin()
	        		+ "somme liés par la police d’assurance n°"+contract.getIdContract() +" ."
	                + "La police souscrite concerne le risque suivant :" + contract.getTypeContract()
	        		+ "ant : ....................................\r\n"
	        		+ "Le remboursement en cas de sinistre s’effectuera selon les conditions suivantes\r\n"
	        		+ " \r\n"
	        		+ ": l’assureur verse àl’assuré une indemnité. Le paiement de la prime qui est de " + contract.getPrimeContract()
	        		+ " par l’assuré se fera le 03 Mars de chaque\r\n"
	        		+ "année.\r\n"
	        		+ " \r\n"
	        		+"Ce contrat est valide la période du "+ contract.getCreationDate() +" au " + contract.getExpirationDate()
	        		+ "Fait à Tunis le" + contract.getCreationDate()
	        		+"Lu et approuvé Lu et approuvé Assuré Assureur\r\n"
	        		+ "";
	        Paragraph paragraph2 = new Paragraph(content, fontParagraph);
	        paragraph2.setAlignment(Paragraph.ALIGN_LEFT);
	        
	       
	        
	        
	        
	        Paragraph paragraph3 = new Paragraph(_customer.getFirstName() +" "+ _customer.getLastName(), fontParagraph);
	        paragraph3.setAlignment(Paragraph.ALIGN_LEFT);

	        document.add(paragraph);
	        document.add(paragraph2);
	        document.add(paragraph3);
	        //contract.setDocumentContract(document.toString());
	        document.close();
	        contract.setDocumentContract(content);
	        
	        contractRepository.save(contract);
	    }




	
		@Override
		public int CountContractsBetween(String EndDate, String BiginingDate) {
			List<Contract> liste=(List<Contract>) contractRepository.findAll();
			
			int counter=0;
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
			
			for(Contract itr :liste)
			{
			
			if (itr.getCreationDate().isAfter(LocalDate.parse(BiginingDate, formatter))&&itr.getCreationDate().isBefore((LocalDate.parse(EndDate, formatter))))
				
				counter++;
			}
			
			return counter;
		}
	
		@Override 
		public HashMap<Type_Contract, Object> viewContractsByTypes() {
			List<Contract> ls=(List<Contract>) contractRepository.findAll();
			List<Contract> lifeI = new ArrayList<Contract>();
			List<Contract> VehI = new ArrayList<Contract>();
			List<Contract> MedI = new ArrayList<Contract>();
			List<Contract> propI = new ArrayList<Contract>();
			List<Contract> AgrI = new ArrayList<Contract>();
			HashMap<String, Object> lifeI_map = new HashMap<String, Object>();
			HashMap<String, Object> VehI_map = new HashMap<String, Object>();
			HashMap<String, Object> MedI_map = new HashMap<String, Object>();
			HashMap<String, Object> propI_map = new HashMap<String, Object>();
			HashMap<String, Object> AgrI_map = new HashMap<String, Object>();
			HashMap<Type_Contract, Object> AllListe = new HashMap<Type_Contract, Object>();
			for(Contract itr :ls)
			{
			
				if (itr.getTypeContract()== Type_Contract.lifeInsurance)
					{lifeI.add(itr);}
			
				else if (itr.getTypeContract()== Type_Contract.vehiculeInsurance)
					{VehI.add(itr);}
			
				else if (itr.getTypeContract()== Type_Contract.medicalInsurance)
					{MedI.add(itr);}
			
				else if (itr.getTypeContract()== Type_Contract.propertyInsurance)
					{propI.add(itr);}
				else if (itr.getTypeContract()== Type_Contract.agricultureInsurance)
					{AgrI.add(itr);}
			}
			
			lifeI_map.put("count", lifeI.size());
			lifeI_map.put("contracts", lifeI);
			
			VehI_map.put("count", VehI.size());
			VehI_map.put("contracts", VehI);
			
			MedI_map.put("count", MedI.size());
			MedI_map.put("contracts", MedI);
			
			propI_map.put("count", propI.size());
			propI_map.put("contracts", propI);
			
			AgrI_map.put("count", AgrI.size());
			AgrI_map.put("contracts", AgrI);
			
			AllListe.put(Type_Contract.lifeInsurance, lifeI_map);
			AllListe.put(Type_Contract.vehiculeInsurance, VehI_map);
			AllListe.put(Type_Contract.medicalInsurance, MedI_map);
			AllListe.put(Type_Contract.propertyInsurance, propI_map);
			AllListe.put(Type_Contract.agricultureInsurance, AgrI_map);
			
			return AllListe;
		
		}
		

		
		@Override
		public List<Contract> findCustomerContracts(long ind) {
			List<Contract> ls= new ArrayList<Contract>();
			ls=(List<Contract>) contractRepository.findAll();
			List<Contract> retoure = new ArrayList<Contract>();
			for(Contract itr :ls)
			{
			
			if (itr.getC_customerAccount().getCustomer().getIdCustomer() ==ind)
				retoure.add(itr);
			}
			
			
			
			return retoure;
		}
		
		
}
