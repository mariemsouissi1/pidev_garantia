package tn.pidev.entities;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;



@Entity 
@Table (name= "Credit" )
public class Credit {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "idCredit", nullable=false)
	private long idCredit;
	private float amountCredit;
	private float amountRemainingCredit;
	@DateTimeFormat(pattern = "yyyy-MM-dd")  
	private Date startDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")  
	private Date lastDueDate;
	public Date getLastDueDate() {
		return lastDueDate;
	}
	public void setLastDueDate(Date lastDueDate) {
		this.lastDueDate = lastDueDate;
	}
	@Enumerated(EnumType.STRING)
	private TraitementCredit traitementCredit;
	
	@Enumerated(EnumType.STRING)
	private StatutCredit statutCredit;

	@Enumerated(EnumType.STRING)
	private VerificationCredit verificationCredit;
	
	@OneToMany(mappedBy="creditTransaction")
	private List<TransactionCredit> transactions;

	@ManyToOne 
	CustomerAccount customerCredit;

	public TraitementCredit getTraitementCredit() {
		return traitementCredit;
	}
	public void setTraitementCredit(TraitementCredit traitementCredit) {
		this.traitementCredit = traitementCredit;
	}
	public long getIdCredit() {
		return idCredit;
	}
	public void setIdCredit(long idCredit) {
		this.idCredit = idCredit;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public StatutCredit getStatutCredit() {
		return statutCredit;
	}
	public void setStatutCredit(StatutCredit statutCredit) {
		this.statutCredit = statutCredit;
	}
	public VerificationCredit getVerificationCredit() {
		return verificationCredit;
	}
	public void setVerificationCredit(VerificationCredit verificationCredit) {
		this.verificationCredit = verificationCredit;
	}
	public List<TransactionCredit> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<TransactionCredit> transactions) {
		this.transactions = transactions;
	}
	public CustomerAccount getCustomerCredit() {
		return customerCredit;
	}
	public void setCustomerCredit(CustomerAccount customerCredit) {
		this.customerCredit = customerCredit;
	}
	public float getAmountRemainingCredit() {
		return amountRemainingCredit;
	}
	public void setAmountRemainingCredit(float amountRemainingCredit) {
		this.amountRemainingCredit = amountRemainingCredit;
	}
	public float getAmountCredit() {
		return amountCredit;
	}
	public void setAmountCredit(float amountCredit) {
		this.amountCredit = amountCredit;
	}
	

 

}