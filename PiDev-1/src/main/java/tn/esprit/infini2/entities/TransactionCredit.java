package tn.esprit.infini2.entities;


import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
public class TransactionCredit {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "idTransaction")
	private long idTransaction;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")    
	private Date dateTransaction;
	private Long sourceAccountNumber;
	
	private Long destinedAccountNumber;
	
	private BigDecimal amountTransaction;
	private BigDecimal soldeCompteEmetteur;
	private BigDecimal soldeCompteRecepteur;
	private String description;
	
    @Enumerated(EnumType.STRING)
	private StatutTransaction statutTransaction;
    


	
	@ManyToOne
	Credit creditTransaction;
	
	
	public Long getSourceAccountNumber() {
		return sourceAccountNumber;
	}

	public void setSourceAccountNumber(Long sourceAccountNumber) {
		this.sourceAccountNumber = sourceAccountNumber;
	}

	public Long getDestinedAccountNumber() {
		return destinedAccountNumber;
	}

	public void setDestinedAccountNumber(Long destinedAccountNumber) {
		this.destinedAccountNumber = destinedAccountNumber;
	}



	public BigDecimal getAmountTransaction() {
		return amountTransaction;
	}

	public void setAmountTransaction(BigDecimal amountTransaction) {
		this.amountTransaction = amountTransaction;
	}



	public BigDecimal getSoldeCompteEmetteur() {
		return soldeCompteEmetteur;
	}

	public void setSoldeCompteEmetteur(BigDecimal soldeCompteEmetteur) {
		this.soldeCompteEmetteur = soldeCompteEmetteur;
	}

	public BigDecimal getSoldeCompteRecepteur() {
		return soldeCompteRecepteur;
	}

	public void setSoldeCompteRecepteur(BigDecimal soldeCompteRecepteur) {
		this.soldeCompteRecepteur = soldeCompteRecepteur;
	}

	public long getIdTransaction() {
		return idTransaction;
	}

	public void setIdTransaction(long idTransaction) {
		this.idTransaction = idTransaction;
	}



	public Date getDateTransaction() {
		return dateTransaction;
	}

	public void setDateTransaction(Date dateTransaction) {
		this.dateTransaction = dateTransaction;
	}

	public StatutTransaction getStatutTransaction() {
		return statutTransaction;
	}

	public void setStatutTransaction(StatutTransaction statutTransaction) {
		this.statutTransaction = statutTransaction;
	}


	public Credit getCreditTransaction() {
		return creditTransaction;
	}

	public void setCreditTransaction(Credit creditTransaction) {
		this.creditTransaction = creditTransaction;
	}





	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	



	
	
}