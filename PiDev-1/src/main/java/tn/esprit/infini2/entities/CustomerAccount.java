package tn.esprit.infini2.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

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

	@OneToMany(cascade = CascadeType.ALL, mappedBy= "c_customerAccount")
	private Set<Contract> contracts;
	@OneToMany(cascade = CascadeType.ALL, mappedBy= "s_customerAccount")
	private Set<Sinister> sinister;
	@OneToMany(mappedBy="customerCredit")
	private List<Credit> customersCredits;
	@OneToMany(mappedBy="customerClaim")
	private List<Claim> customersClaimss;
	@ManyToMany
	private Set<Offer> offer;
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

}
