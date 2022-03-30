package tn.esprit.infini2.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.infini2.entities.Customer;
import tn.esprit.infini2.entities.customerAccount;
import tn.esprit.infini2.repositories.CustomerAccountRepository;

import static jdk.internal.org.objectweb.asm.commons.GeneratorAdapter.OR;

public class CustomerAccountServiceImp implements ICustomerAccountService{
	@Autowired
	CustomerAccountRepository customerAccountRepository;
	@Override
	public List<customerAccount> retrieveAllCustomerAccounts() {
		return (List<customerAccount>) customerAccountRepository.findAll();
	}

	@Override
	public customerAccount addCustomerAccount(customerAccount c) {
		customerAccountRepository.save(c);
		return c;
	}

	@Override
	public void deleteCustomerAccount(Long id) {
		customerAccountRepository.deleteById(id);	
		
	}

	@Override
	public customerAccount updateCustomerAccount(customerAccount u) {
		customerAccountRepository.save(u);
		return u;
	}

	@Override
	public customerAccount retrieveCustomerAccount(Long id) {
		return customerAccountRepository.findById(id).orElse(null);
	}


	public int calculScoreGovernorate(Customer customer){
		if (customer.getGovernorate().equals("Tunis") ||
			customer.getGovernorate().equals("Ariana")||
			customer.getGovernorate().equals("Nabeul")||
			customer.getGovernorate().equals("Sousse")||
			customer.getGovernorate().equals("Monastir"))
			return 4;

		else if(customer.getGovernorate().equals("BenArous")||
				customer.getGovernorate().equals("Manouba")||
				customer.getGovernorate().equals("Bizerte")||
				customer.getGovernorate().equals("Sfax")||
				customer.getGovernorate().equals("Tozeur")||
				customer.getGovernorate().equals("Zaghouan")||
				customer.getGovernorate().equals("Mahdia"))
			return 3;

		else if(customer.getGovernorate().equals("Jendouba")||
				customer.getGovernorate().equals("Beja")||
				customer.getGovernorate().equals("Siliana")||
				customer.getGovernorate().equals("Kairouan")||
				customer.getGovernorate().equals("Médenine")||
				customer.getGovernorate().equals("Kef"))
			return 2;

		else if(customer.getGovernorate().equals("Kasserine")||
				customer.getGovernorate().equals("SidiBouzid")||
				customer.getGovernorate().equals("Gafsa")||
				customer.getGovernorate().equals("Gabes")||
				customer.getGovernorate().equals("Kebili")||
				customer.getGovernorate().equals("Tataouine"))
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
		if (customer.getGender().equals("Male"))
			return 1;
		if (customer.getGender().equals("Female"))
			return (float) 0.9;
		if (customer.getGender().equals("Other"))
			return (float) 0.8;
		return 0;
	}

	public float calculScoreAge(Customer customer){
		LocalDate today = LocalDate.now();
		LocalDate birthday = customer.getBirthDate();
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
		return score;
	}

}
