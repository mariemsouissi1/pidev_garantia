package tn.esprit.infini2.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import tn.esprit.infini2.dto.GeneralScoreStat;
import tn.esprit.infini2.entities.*;
import tn.esprit.infini2.repositories.CustomerAccountRepository;
import tn.esprit.infini2.repositories.CustomerRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

//import static jdk.internal.org.objectweb.asm.commons.GeneratorAdapter.OR;

@Service
public class CustomerAccountServiceImp implements ICustomerAccountService{
	@Autowired
	CustomerAccountRepository customerAccountRepository;

	@Autowired
	EntityManager em;

	@Autowired
	CustomerRepository customerRepository;
	@Override
	public List<CustomerAccount> retrieveAllCustomerAccounts() {
		return  customerAccountRepository.findAll();
	}

	@Override
	public CustomerAccount addCustomerAccount(CustomerAccount c) {
		customerAccountRepository.save(c);
		return c;
	}

	@Override
	public void deleteCustomerAccount(Long id) {
		customerAccountRepository.deleteById(id);

	}

	@Override
	public CustomerAccount updateCustomerAccount(CustomerAccount u) {
		customerAccountRepository.save(u);
		return u;
	}

	@Override
	public CustomerAccount retrieveCustomerAccount(Long id) {
		return customerAccountRepository.findById(id).orElse(null);
	}


	public  List<GeneralScoreStat> customerScoreStat ()
	{
		List<GeneralScoreStat> generalScoreStats = customerAccountRepository.retrieveScoreStat();

		return (generalScoreStats);
	}

	public int calculScoreGovernorate(Customer customer){

		if (customer.getGovernorate()==Governorates.Ariana||
				customer.getGovernorate()==Governorates.Tunis||
				customer.getGovernorate()==Governorates.Nabeul||
				customer.getGovernorate()==Governorates.Sousse||
				customer.getGovernorate()==Governorates.Monastir)
			return 4;

		else if(customer.getGovernorate()==Governorates.BenArous||
				customer.getGovernorate()==Governorates.Manouba||
				customer.getGovernorate()==Governorates.Bizerte||
				customer.getGovernorate()==Governorates.Sfax||
				customer.getGovernorate()==Governorates.Tozeur||
				customer.getGovernorate()==Governorates.Zaghouan||
				customer.getGovernorate()==Governorates.Mahdia)
			return 3;

		else if(customer.getGovernorate()==Governorates.Jendouba||
				customer.getGovernorate()==Governorates.Beja||
				customer.getGovernorate()==Governorates.Siliana||
				customer.getGovernorate()==Governorates.Kairouan||
				customer.getGovernorate()==Governorates.Medenine||
				customer.getGovernorate()==Governorates.Kef)
			return 2;

		else if(customer.getGovernorate()==Governorates.Kasserine||
				customer.getGovernorate()==Governorates.SidiBouzid||
				customer.getGovernorate()==Governorates.Gafsa||
				customer.getGovernorate()==Governorates.Gabes||
				customer.getGovernorate()==Governorates.Kebili||
				customer.getGovernorate()==Governorates.Tataouine)
			return 1;
		return 0;
	}

	public int calculScoreSalary(Customer customer){
		if (customer.getMonthlyIncome() > 0 && customer.getMonthlyIncome() <= 500)
			return 1;
		if (customer.getMonthlyIncome() > 500 && customer.getMonthlyIncome() <= 1000)
			return 2;
		if (customer.getMonthlyIncome() > 1000 && customer.getMonthlyIncome() <= 1500)
			return 3;
		if (customer.getMonthlyIncome() > 1500 && customer.getMonthlyIncome() <= 2000)
			return 4;
		return 0;
	}

	public float calculScoreGender(Customer customer){

		if (customer.getGender()==Gender.Male)
			return 1;
		if (customer.getGender()==Gender.Female)
			return (float) 0.9;
		if (customer.getGender()==Gender.Other)
			return (float) 0.8;
		return 0;
	}

	public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
		return dateToConvert.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDate();
	}


	public float calculScoreAge(Customer customer){
		LocalDate birthday =convertToLocalDateViaInstant(customer.getBirthDate());
		LocalDate today = LocalDate.now();
		//LocalDate birthday = LocalDate.now();//customer.getBirthDate();
		Period period = Period.between(birthday, today);
		if (period.getYears() > 20 && period.getYears() < 30) {
			return 1;
		}
		else if (period.getYears() > 30 && period.getYears() < 40) {
			return (float) 0.9;
		}
		else if (period.getYears() > 40 && period.getYears() < 50) {
			return (float) 0.8;
		}
		else if (period.getYears() > 60 && period.getYears() < 70) {
			return (float) 0.7;
		}
		else if (period.getYears() > 70 && period.getYears() < 80) {
			return (float) 0.6;
		}
		else if (period.getYears() > 80 && period.getYears() < 90) {
			return (float) 0.5;
		}
		return 0;
	}

	@Override
	public Float calculScore(Customer customer) {
		int scoreGovernorate=calculScoreGovernorate(customer);
		int scoreSalary=calculScoreSalary(customer);
		float scoreGender=calculScoreGender(customer);
		float scoreAge=calculScoreAge(customer);
		float score=scoreGovernorate + scoreSalary + scoreGender + scoreAge;
		//ken bin el 0.3 wel 0 il modifie el champ fel table client
		//stat aal les clients
		//avancement partie securite
		return score;
	}

	public List<GeneralScoreStat> retrieveScoreStat() throws JsonProcessingException {
		//List resultList = em.createNativeQuery("SELECT COUNT(*) AS n , score_type as scoreTypeName FROM customer_account c GROUP BY c.score_type").getResultList();
		List<GeneralScoreStat> generalScoreStats=new ArrayList<>();
		List<CustomerAccount> customerAccounts=customerAccountRepository.findAll();

		Long countcustomerAccountsMediocre=customerAccounts.stream().filter(e-> e.getScoreType().equals(ScoreType.Mediocre)).count();
		Long countcustomerAccountsAverage=customerAccounts.stream().filter(e-> e.getScoreType().equals(ScoreType.Average)).count();
		Long countcustomerAccountsGood=customerAccounts.stream().filter(e-> e.getScoreType().equals(ScoreType.Good)).count();
		Long countcustomerAccountsExcellent=customerAccounts.stream().filter(e-> e.getScoreType().equals(ScoreType.Excellent)).count();

		GeneralScoreStat customerAccountsMediocre=new GeneralScoreStat();
		customerAccountsMediocre.setN(countcustomerAccountsMediocre.intValue());
		customerAccountsMediocre.setScoreTypeName(ScoreType.Mediocre.name());
		customerAccountsMediocre.setPercent((countcustomerAccountsMediocre*100)/customerAccounts.size()+"%");
		generalScoreStats.add(customerAccountsMediocre);

		GeneralScoreStat customerAccountsAverage=new GeneralScoreStat();
		customerAccountsAverage.setN(countcustomerAccountsAverage.intValue());
		customerAccountsAverage.setScoreTypeName(ScoreType.Average.name());
		customerAccountsAverage.setPercent((countcustomerAccountsAverage*100)/customerAccounts.size()+"%");
		generalScoreStats.add(customerAccountsAverage);

		GeneralScoreStat customerAccountsGood=new GeneralScoreStat();
		customerAccountsGood.setN(countcustomerAccountsGood.intValue());
		customerAccountsGood.setScoreTypeName(ScoreType.Good.name());
		customerAccountsGood.setPercent((countcustomerAccountsGood*100)/customerAccounts.size()+"%");
		generalScoreStats.add(customerAccountsGood);

		GeneralScoreStat customerAccountsExcellent=new GeneralScoreStat();
		customerAccountsExcellent.setN(countcustomerAccountsExcellent.intValue());
		customerAccountsExcellent.setScoreTypeName(ScoreType.Excellent.name());
		customerAccountsExcellent.setPercent((countcustomerAccountsExcellent*100)/customerAccounts.size()+"%");
		generalScoreStats.add(customerAccountsExcellent);


		return generalScoreStats ;
	}
	//	//


}
