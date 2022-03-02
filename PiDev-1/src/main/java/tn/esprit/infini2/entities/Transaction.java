package tn.esprit.infini2.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class Transaction {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "idTransaction")
	private Long idTransaction;

	private String fileTransaction;
	
	@Temporal (TemporalType.DATE)
	private Date DateTransaction;
	@ManyToOne
	Credit credit;
	public String getFileTransaction() {
		return fileTransaction;
	}

	public void setFileTransaction(String fileTransaction) {
		this.fileTransaction = fileTransaction;
	}

	
	
}