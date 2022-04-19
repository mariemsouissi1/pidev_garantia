package tn.esprit.infini2.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
public class TransactionCredit {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "idTransaction")
	private long idTransaction;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")    
	private long dateTransaction;
	private long sourceAccountNumber;
	private String sourceCurrency;
	private long destinedAccountNumber;
	private double amountTransaction;
	private String description;
	
    @Enumerated(EnumType.STRING)
	private StatutTransaction statutTransaction;
    public TypeTransaction getTypeTransaction() {
		return typeTransaction;
	}

	public void setTypeTransaction(TypeTransaction typeTransaction) {
		this.typeTransaction = typeTransaction;
	}

	@Enumerated(EnumType.STRING)
    private TypeTransaction typeTransaction;
	
	private String url;
	@ManyToOne
	Credit creditTransaction;
	
	
	public long getIdTransaction() {
		return idTransaction;
	}

	public void setIdTransaction(long idTransaction) {
		this.idTransaction = idTransaction;
	}

	public long getDateTransaction() {
		return dateTransaction;
	}

	public void setDateTransaction(long dateTransaction) {
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

	public long getSourceAccountNumber() {
		return sourceAccountNumber;
	}

	public void setSourceAccountNumber(long sourceAccountNumber) {
		this.sourceAccountNumber = sourceAccountNumber;
	}

	

	public long getDestinedAccountNumber() {
		return destinedAccountNumber;
	}

	public void setDestinedAccountNumber(long destinedAccountNumber) {
		this.destinedAccountNumber = destinedAccountNumber;
	}

	public String getSourceCurrency() {
		return sourceCurrency;
	}

	public void setSourceCurrency(String sourceCurrency) {
		this.sourceCurrency = sourceCurrency;
	}

	public double getAmountTransaction() {
		return amountTransaction;
	}

	public void setAmountTransaction(double amountTransaction) {
		this.amountTransaction = amountTransaction;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}



	
	
}