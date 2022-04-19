package tn.esprit.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Required;




@SuppressWarnings("deprecation")
@Entity
@Table(name="CustomerAccount")
public class CustomerAccount implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idCustomerAccount", nullable=false)
	
	private long idCustomerAccount; //clé primaire
	
	private long cin;
	
	private String FirstNameCustomer;
	
	private String LastNameCustomer;
	@NotEmpty
	@Email
	private String emailAccount;

	
    @Enumerated(EnumType.STRING)
	private StatutCustomer statutCustomer;

    @Enumerated(EnumType.STRING)
	private Fidelite fidelite;
    
    private double SalaryCustomer;
    
    @OneToMany(mappedBy="customerCredit")
	private List<Credit> creditsCustomer;
    
    @OneToMany(mappedBy="customerAccountLoan")
    private List<LoanSimulation> loanSimulatorsListCustomer;
    
	public List<LoanSimulation> getLoanSimulatorsListCustomer() {
		return loanSimulatorsListCustomer;
	}
	public void setLoanSimulatorsListCustomer(List<LoanSimulation> loanSimulatorsListCustomer) {
		this.loanSimulatorsListCustomer = loanSimulatorsListCustomer;
	}
	public List<Credit> getCreditsCustomer() {
		return creditsCustomer;
	}
	public void setCreditsCustomer(List<Credit> creditsCustomer) {
		this.creditsCustomer = creditsCustomer;
	}
	public long getIdCustomerAccount() {
		return idCustomerAccount;
	}
	public void setIdCustomerAccount(long idCustomerAccount) {
		this.idCustomerAccount = idCustomerAccount;
	}

	public Fidelite getFidelite() {
		return fidelite;
	}
	public void setFidelite(Fidelite fidelite) {
		this.fidelite = fidelite;
	}
	public StatutCustomer getStatutCustomer() {
		return statutCustomer;
	}
	public void setStatutCustomer(StatutCustomer statutCustomer) {
		this.statutCustomer = statutCustomer;
	}
	public String getEmailAccount() {
		return emailAccount;
	}
	@Required
	public void setEmailAccount(String emailAccount) {
		this.emailAccount = emailAccount;
	}
	@Required
	public double getSalaryCustomer() {
		return SalaryCustomer;
	}
	public void setSalaryCustomer(double salaryCustomer) {
		SalaryCustomer = salaryCustomer;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getFirstNameCustomer() {
		return FirstNameCustomer;
	}
	public void setFirstNameCustomer(String firstNameCustomer) {
		FirstNameCustomer = firstNameCustomer;
	}
	public String getLastNameCustomer() {
		return LastNameCustomer;
	}
	public void setLastNameCustomer(String lastNameCustomer) {
		LastNameCustomer = lastNameCustomer;
	}
	public long getCin() {
		return cin;
	}
	public void setCin(long cin) {
		this.cin = cin;
	}
	
	
	
}
