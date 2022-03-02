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
@Table(name="Premium")
public class Premium implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idPremium")
	private Long idPremium; //cléPrimaire
	private Float primAmount;
	@Temporal(TemporalType.DATE)
	private Date primDate;
	
	@OneToOne(mappedBy="premium")
	private Contract contract;
	
	public Long getIdPremium() {
		return idPremium;
	}
	public void setIdPremium(Long idPremium) {
		this.idPremium = idPremium;
	}
	public Float getPrimAmount() {
		return primAmount;
	}
	public void setPrimAmount(Float primAmount) {
		this.primAmount = primAmount;
	}
	public Date getPrimDate() {
		return primDate;
	}
	public void setPrimDate(Date primDate) {
		this.primDate = primDate;
	}
}
