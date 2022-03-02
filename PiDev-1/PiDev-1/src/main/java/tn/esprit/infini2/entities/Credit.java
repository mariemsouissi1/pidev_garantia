package tn.esprit.infini2.entities;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity 
@Table (name= "Credit" )
public class Credit {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "idCredit")
	private float amountCredit;
	private float amountRemainingCredit;
	@Temporal (TemporalType.DATE)
	private Date startDate;
	private Date firstDueDate;
	private Date lastDueDate;
	@Enumerated(EnumType.STRING)
	private StatutCredit statutCredit;
	@Enumerated(EnumType.STRING)
	
	private VerificationCredit verificationCredit;
	@OneToMany(mappedBy="credit")
	private List<Transaction> transactions;

	@ManyToOne 
	customerAccount customerCredit;



	public float getAmountRemainingCredit() {
		return amountRemainingCredit;
	}
	public void setAmountRemainingCredit(float amountRemainingCredit) {
		this.amountRemainingCredit = amountRemainingCredit;
	}
	public Date getLastDueDate() {
		return lastDueDate;
	}
	public void setLastDueDate(Date lastDueDate) {
		this.lastDueDate = lastDueDate;
	}
	public Date getFirstDueDate() {
		return firstDueDate;
	}
	public void setFirstDueDate(Date firstDueDate) {
		this.firstDueDate = firstDueDate;
	}

 

}