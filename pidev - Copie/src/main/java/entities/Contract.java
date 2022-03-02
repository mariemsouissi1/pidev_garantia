package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Contract")
public class Contract implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idContract")
	private Long idContract; //clé primaire
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
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	public Type_Contract getTypeContract() {
		return typeContract;
	}
	public void setTypeContract(Type_Contract typeContract) {
		this.typeContract = typeContract;
	}
	public String getDocumentContract() {
		return documentContract;
	}
	public void setDocumentContract(String documentContract) {
		this.documentContract = documentContract;
	}
	private Long durationContract;
	@Temporal(TemporalType.DATE)
	private Date creationDate;
	@Temporal(TemporalType.DATE)
	private Date expirationDate;
	@Enumerated(EnumType.STRING)
	private Type_Contract typeContract;
	private String documentContract;
	@OneToOne
	private Premium premium;
	@ManyToOne
	customerAccount customerAccount;
}

