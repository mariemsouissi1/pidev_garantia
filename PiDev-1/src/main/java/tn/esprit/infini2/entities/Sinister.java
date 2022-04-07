package tn.esprit.infini2.entities;

import java.io.Serializable;
import java.util.Date;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;



@Entity
public class Sinister implements Serializable{
	
	public Integer getIdSinister() {
		return idSinister;
	}




	public void setIdSinister(Integer idSinister) {
		this.idSinister = idSinister;
	}




	public void setDelaiDeclaration(Integer delaiDeclaration) {
		this.delaiDeclaration = delaiDeclaration;
	}




	@ManyToOne
	CustomerAccount S_customerAccount;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="Sinister_ID")
	private Integer idSinister; // Clé primaire
	
	@Autowired
	@Temporal (TemporalType.DATE )
	@JsonFormat(shape=Shape.STRING, pattern="dd-MM-yyyy")
	private Date declarationDate;
	
	@Autowired(required = false)
	@Temporal (TemporalType.DATE)
	@JsonFormat(shape=Shape.STRING, pattern="dd-MM-yyyy")
	private Date sinisterDate;
	
	@Autowired(required = false)
	@Temporal (TemporalType.DATE)
	@JsonFormat(shape=Shape.STRING, pattern="dd-MM-yyyy")
	private Date IndemnisationDate;
	
	@Autowired(required = false)
	@Column(name="delaiDeclaration",nullable=true)
	private Integer delaiDeclaration= 2; // 
	
	@Autowired(required = false)
	@Column(name="sinisterPlace",nullable=true)
	private String sinisterPlace;
	
	
	@Autowired(required = false)
	@Column(name="chargeSinister",nullable=true)
	private Float chargeSinister;
	
	@Autowired(required = false)
	@Column(name="sinisterDescription",nullable=true)
	private String sinisterDescription;
	@Enumerated(EnumType.STRING)
	@Column(name="sinisterStatus",nullable=true)
	private SinisterStatus sinisterStatus; //rejeté  payé in process
	
	@Column(name="sinisterType",nullable=true)
	private String sinisterType;
	
	@Column(name="causeRejet",nullable=true)
	private String causeRejet;
	
	@Column(name="SinisterIndemnity",nullable=true)
	private Float SinisterIndemnity;
	
	

	






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




	public Float getChargeSinister() {
		return chargeSinister;
	}




	public void setChargeSinister(Float chargeSinister) {
		this.chargeSinister = chargeSinister;
	}




	public String getSinisterDescription() {
		return sinisterDescription;
	}




	public void setSinisterDescription(String sinisterDescription) {
		this.sinisterDescription = sinisterDescription;
	}







	public String getSinisterType() {
		return sinisterType;
	}




	public void setSinisterType(String sinisterType) {
		this.sinisterType = sinisterType;
	}




	public String getCauseRejet() {
		return causeRejet;
	}




	public void setCauseRejet(String causeRejet) {
		this.causeRejet = causeRejet;
	}
	

	public SinisterStatus getSinisterStatus() {
		return sinisterStatus;
	}




	public void setSinisterStatus(SinisterStatus sinisterStatus) {
		this.sinisterStatus = sinisterStatus;
	}




	public Float getSinisterIndemnity() {
		return SinisterIndemnity;
	}




	public void setSinisterIndemnity(Float sinisterIndemnity) {
		SinisterIndemnity = sinisterIndemnity;
	}




	



	public CustomerAccount getS_customerAccount() {
		return S_customerAccount;
	}




	public void setS_customerAccount(CustomerAccount s_customerAccount) {
		S_customerAccount = s_customerAccount;
	}




	public Sinister() {
		super();
		// TODO Auto-generated constructor stub
	}












	




	
	
	
	
	


}