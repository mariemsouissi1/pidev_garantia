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





@Entity(name ="SINISTER")
@Table(name ="SINISTER")

public class Sinister implements Serializable {

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public long getIdSinister() {
		return idSinister;
	}



	public String getTypeSinsiter() {
		return typeSinsiter;
	}



	public String getPdf() {
		return pdf;
	}



	public Date getDateSinister() {
		return dateSinister;
	}



	


	public void setIdSinister(long idSinister) {
		this.idSinister = idSinister;
	}



	public void setTypeSinsiter(String typeSinsiter) {
		this.typeSinsiter = typeSinsiter;
	}



	public void setPdf(String pdf) {
		this.pdf = pdf;
	}



	public void setDateSinister(Date dateSinister) {
		this.dateSinister = dateSinister;
	}



	



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idSinister")
	private long idSinister;
	private String typeSinsiter;
	private String pdf;
	
	
	

	@Temporal(TemporalType.DATE)
	private Date dateSinister;
	
	
	@ManyToOne
	customerAccount S_customerAccount;
	@OneToOne
	private Indemnity indemnity;
	

}
