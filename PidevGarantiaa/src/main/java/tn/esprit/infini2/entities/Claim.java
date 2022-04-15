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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="Claim")
public class Claim implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="claim_id")
	private Long claim_id;//cle primaire
	private String claim_description;
	@Enumerated(EnumType.STRING)
	public Claim_state claim_state;
	//Treated,Ongoing,Untreated
	@Enumerated(EnumType.STRING)
	private Claim_type claim_type;
	@Enumerated(EnumType.STRING)
	private Claim_contrat_type claim_contrat_type;
	private Boolean claim_visibility;
	@Temporal(TemporalType.DATE)
	
	private Date claim_date;
	
	@ManyToOne 
	CustomerAccount customerClaim;
	

	
	@Override
	public String toString() {
		return "Claim [claim_id=" + claim_id + ", claim_description=" + claim_description + ", claim_state="
				+ claim_state + ", claim_type=" + claim_type + ", claim_contrat_type=" + claim_contrat_type
				+ ", claim_visibility=" + claim_visibility + ", claim_date=" + claim_date + "]";
	}
	public Date getClaim_date() {
		return claim_date;
	}
	public void setClaim_date(Date claim_date) {
		this.claim_date = claim_date;
	}
	public CustomerAccount getCustomerClaim() {
		return customerClaim;
	}
	public void setCustomerClaim(CustomerAccount customerClaim) {
		this.customerClaim = customerClaim;
	}

	public Claim_contrat_type getClaim_contrat_type() {
		return claim_contrat_type;
	}
	public void setClaim_contrat_type(Claim_contrat_type claim_contrat_type) {
		this.claim_contrat_type = claim_contrat_type;
	}
	public Boolean getClaim_visibility() {
		return claim_visibility;
	}
	public void setClaim_visibility(Boolean claim_visibility) {
		this.claim_visibility = claim_visibility;
	}
	public Long getClaim_id() {
		return claim_id;
	}
	public void setClaim_id(Long claim_id) {
		this.claim_id = claim_id;
	}
	public String getClaim_description() {
		return claim_description;
	}
	public void setClaim_description(String claim_description) {
		this.claim_description = claim_description;
	}
	public Claim_state getClaim_state() {
		return claim_state;
	}
	public void setClaim_state(Claim_state string) {
		this.claim_state = string;
	}
	public Claim_type getClaim_type() {
		return claim_type;
	}
	public void setClaim_type(Claim_type claim_type) {
		this.claim_type = claim_type;
	}

}
