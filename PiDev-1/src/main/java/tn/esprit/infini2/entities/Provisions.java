package tn.esprit.infini2.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Provisions")
public class Provisions implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idProvisions")
	private Long idProvisions; //cléPrimaire
	private Float provisionsAmountt;
	@Temporal(TemporalType.DATE)
	private Date provisionsDate;
	@OneToOne
	private Premium premium;
	public Long getIdProvisions() {
		return idProvisions;
	}
	public void setIdProvisions(Long idProvisions) {
		this.idProvisions = idProvisions;
	}
	public Float getProvisionsAmountt() {
		return provisionsAmountt;
	}
	public void setProvisionsAmountt(Float provisionsAmountt) {
		this.provisionsAmountt = provisionsAmountt;
	}
	public Date getProvisionsDate() {
		return provisionsDate;
	}
	public void setProvisionsDate(Date provisionsDate) {
		this.provisionsDate = provisionsDate;
	}
	}