package tn.esprit.infini2.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name="customerAccount")
public class CustomerAccount implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idCustomerAccount")
	private Long idCustomerAccount; //clé primaire
	private String contratAssocie;
	@Temporal(TemporalType.DATE)
	private Date dateCreationCompte;
	private Float solde;
	private Float debit;
	private Float score;
	
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
    

	public ScoreType getScoreType() {
		return scoreType;
	}

	public void setScoreType(ScoreType scoreType) {
		this.scoreType = scoreType;
	}

	@Enumerated(EnumType.STRING)
	//@Column( nullable = false)
	private ScoreType scoreType;
	@OneToOne(mappedBy="customerAccount")
	Customer customer;

	@OneToMany(cascade = CascadeType.ALL, mappedBy= "C_customerAccount")
	private Set<Contract> contracts;
	@OneToMany(cascade = CascadeType.ALL, mappedBy= "S_customerAccount")
	private Set<Sinister> sinister;

	@OneToMany(mappedBy="customerClaim")
	private List<Claim> customersClaimss;
	@ManyToMany
	private Set<Offer> offers;
	public Long getIdCustomerAccount() {
		return idCustomerAccount;
	}
	public void setIdCustomerAccount(Long idCustomerAccount) {
		this.idCustomerAccount = idCustomerAccount;
	}
	public String getContratAssocie() {
		return contratAssocie;
	}
	public void setContratAssocie(String contratAssocie) {
		this.contratAssocie = contratAssocie;
	}
	public Date getDateCreationCompte() {
		return dateCreationCompte;
	}
	public void setDateCreationCompte(Date dateCreationCompte) {
		this.dateCreationCompte = dateCreationCompte;
	}
	public Float getSolde() {
		return solde;
	}
	public void setSolde(Float solde) {
		this.solde = solde;
	}
	public Float getDebit() {
		return debit;
	}
	public void setDebit(Float debit) {
		this.debit = debit;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public long getCin() {
		return cin;
	}

	public void setCin(long cin) {
		this.cin = cin;
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

	public String getEmailAccount() {
		return emailAccount;
	}

	public void setEmailAccount(String emailAccount) {
		this.emailAccount = emailAccount;
	}

	public StatutCustomer getStatutCustomer() {
		return statutCustomer;
	}

	public void setStatutCustomer(StatutCustomer statutCustomer) {
		this.statutCustomer = statutCustomer;
	}

	public Fidelite getFidelite() {
		return fidelite;
	}

	public void setFidelite(Fidelite fidelite) {
		this.fidelite = fidelite;
	}

	public double getSalaryCustomer() {
		return SalaryCustomer;
	}

	public void setSalaryCustomer(double salaryCustomer) {
		SalaryCustomer = salaryCustomer;
	}

	public List<Credit> getCreditsCustomer() {
		return creditsCustomer;
	}

	public void setCreditsCustomer(List<Credit> creditsCustomer) {
		this.creditsCustomer = creditsCustomer;
	}

	public List<LoanSimulation> getLoanSimulatorsListCustomer() {
		return loanSimulatorsListCustomer;
	}

	public void setLoanSimulatorsListCustomer(List<LoanSimulation> loanSimulatorsListCustomer) {
		this.loanSimulatorsListCustomer = loanSimulatorsListCustomer;
	}

	public Set<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(Set<Contract> contracts) {
		this.contracts = contracts;
	}

	public Set<Sinister> getSinister() {
		return sinister;
	}

	public void setSinister(Set<Sinister> sinister) {
		this.sinister = sinister;
	}

	public List<Claim> getCustomersClaimss() {
		return customersClaimss;
	}

	public void setCustomersClaimss(List<Claim> customersClaimss) {
		this.customersClaimss = customersClaimss;
	}

	public Set<Offer> getOffers() {
		return offers;
	}

	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
