package tn.esprit.infini2.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="customerAccount")
public class customerAccount implements Serializable{
	/**
	 * 
	 */
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
	
	@OneToOne(mappedBy="customerAccount")
    Customer customer;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="C_customerAccount")
	private Set<Contract> contracts;
	@OneToMany(cascade = CascadeType.ALL, mappedBy="S_customerAccount")
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
	
}
