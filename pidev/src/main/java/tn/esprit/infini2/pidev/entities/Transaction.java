package tn.esprit.infini2.pidev.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Transaction {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "idTransaction")
	private float amountTransaction;
	private String fileTransaction;
	
	@Temporal (TemporalType.DATE)
	private Date DateTransaction;

	public String getFileTransaction() {
		return fileTransaction;
	}

	public void setFileTransaction(String fileTransaction) {
		this.fileTransaction = fileTransaction;
	}

	
	
}