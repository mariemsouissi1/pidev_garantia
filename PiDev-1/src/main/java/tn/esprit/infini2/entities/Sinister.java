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

import org.springframework.beans.factory.annotation.Autowired;





@Entity(name ="SINISTER")
@Table(name ="SINISTER")

public class Sinister implements Serializable {

	
	public static long getSerialversionuid() {
		return serialVersionUID;
		
		
		
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
	public Date getDeclarationDate() {
		return declarationDate;
	}
	
	public void setDeclarationDate(Date declarationDate) {
		this.declarationDate = declarationDate;
	}
	
	
	
	
	public Date getSinisterDate() {
		return sinisterDate;
	}
	public void setSinisterDate(Date sinisterDate) {
		this.sinisterDate = sinisterDate;
	}
	
	
	
	
	public Date getIndemnisationDate() {
		return IndemnisationDate;
	}
	public void setIndemnisationDate(Date indemnisationDate) {
		IndemnisationDate = indemnisationDate;
	}
	
	
	
	
	public int getDelaiDeclaration() {
		return delaiDeclaration;
	}
	public void setDelaiDeclaration(int delaiDeclaration) {
		this.delaiDeclaration = delaiDeclaration;
	}
	
	
	
	
	
	
	public String getSinisterPlace() {
		return sinisterPlace;
	}
	public void setSinisterPlace(String sinisterPlace) {
		this.sinisterPlace = sinisterPlace;
	}
	
	
	
	
	
	public double getChargeSinister() {
		return chargeSinister;
	}
	public void setChargeSinister(double chargeSinister) {
		this.chargeSinister = chargeSinister;
	}
	
	
	
	
	
	
	public String getSinisterDescription() {
		return sinisterDescription;
	}
	public void setSinisterDescription(String sinisterDescription) {
		this.sinisterDescription = sinisterDescription;
	}
	
	
	
	
	
	
	
	public String getSinisterStatus() {
		return sinisterStatus;
	}
	
	public void setSinisterStatus(String sinisterStatus) {
		this.sinisterStatus = sinisterStatus;
	}
	
	
	
	
	
	
	
	public String getSinisterType() {
		return sinisterType;
	}
	public void setSinisterType(String sinisterType) {
		this.sinisterType = sinisterType;
	}
	

	
	

	
	
	
	public void setCauseRejet(String causeRejet) {
		this.causeRejet = causeRejet;
	}
	public String getCauseRejet() {
		return causeRejet;
	}
	
	
	
	

	
	
	
	/**
	 * 
	 */
	
	
	

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="Sinister_ID")
	private int id; 
	// PK 
	
	@Autowired
	@Temporal (TemporalType.DATE )
	private Date declarationDate;
	
	@Autowired
	@Temporal (TemporalType.DATE)
	private Date sinisterDate;
	
	@Autowired
	@Temporal (TemporalType.DATE)
	private Date IndemnisationDate;
	
	@Autowired
	@Column(name="delaiDeclaration")
	private int delaiDeclaration; // 
	
	@Autowired
	@Column(name="sinisterPlace")
	private String sinisterPlace;
	
	
	@Autowired
	@Column(name="chargeSinister")
	private double chargeSinister;
	
	@Autowired
	@Column(name="sinisterDescription")
	private String sinisterDescription;
	
	@Column(name="sinisterStatus")
	private String sinisterStatus; 
	//rejeté , payé ,  in process ;;;;
	
	@Column(name="sinisterType")
	private String sinisterType;
	
	@Column(name="causeRejet")
	private String causeRejet;
	
	

	
	
	@ManyToOne
	customerAccount S_customerAccount;
	@OneToOne
	private Indemnity_pay indemnity_pay;
	

}
