package tn.esprit.infini2.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity(name ="INDEMNITY")
@Table(name ="INDEMNITY")
public class Indemnity implements Serializable{
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public long getIdIndemnity() {
		return idIndemnity;
	}
	public Date getDateIndemnity() {
		return dateIndemnity;
	}
	public float getAmountIndemnity() {
		return amountIndemnity;
	}
	
	
	public void setIdIndemnity(long idIndemnity) {
		this.idIndemnity = idIndemnity;
	}
	public void setDateIndemnity(Date dateIndemnity) {
		this.dateIndemnity = dateIndemnity;
	}
	public void setAmountIndemnity(float amountIndemnity) {
		this.amountIndemnity = amountIndemnity;
	}
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idCIndemnity")
	private long idIndemnity;
	@Temporal(TemporalType.DATE)
	private Date dateIndemnity;
	private float amountIndemnity;
	
	
	@OneToOne(mappedBy="indemnity")
	private Sinister sinister;
	@ManyToOne
	CustomerAccount  Customer_account;
	
	
	

}
