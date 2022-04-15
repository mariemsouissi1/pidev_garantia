package tn.esprit.infini2.entities;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Contract")
public class Contract implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idContract")
	private Long idContract; // clé primaire
	private Long durationContract;
	private double PrimeContract;
	public double getPrimeContract() {
		return PrimeContract;
	}

	public void setPrimeContract(double primeContract) {
		PrimeContract = primeContract;
	}

	//@Temporal(TemporalType.DATE)
	private LocalDate creationDate;
	//@Temporal(TemporalType.DATE)
	private LocalDate expirationDate;
	@Enumerated(EnumType.STRING)
	private Type_Contract typeContract;
	private String documentContract;
	@OneToOne
	private Premium premium;
	@ManyToOne
	customerAccount C_customerAccount;

	public Long getIdContract() {
		return idContract;
	}

	public void setIdContract(Long idContract) {
		this.idContract = idContract;
	}

	public Long getDurationContract() {
		return durationContract;
	}

	public void setDurationContract(Long durationContract) {
		this.durationContract = durationContract;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public Premium getPremium() {
		return premium;
	}

	public void setPremium(Premium premium) {
		this.premium = premium;
	}

	public customerAccount getC_customerAccount() {
		return C_customerAccount;
	}

	public void setC_customerAccount(customerAccount c_customerAccount) {
		C_customerAccount = c_customerAccount;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Type_Contract getTypeContract() {
		return typeContract;
	}

	public void setTypeContract(Type_Contract typeContract) {
		this.typeContract = typeContract;
	}

	//@Column(columnDefinition = "TEXT")
	public String getDocumentContract() {
		return documentContract;
	}

	public void setDocumentContract(String documentContract) {
		this.documentContract = documentContract;
	}

}
