package tn.esprit.infini2.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Offer")

public class Offer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="offer_id")
	private Long offer_id;//cle primaire
	private String offer_description;
	private Date offer_expiration_date;
	public Long getOffer_id() {
		return offer_id;
	}
	public void setOffer_id(Long offer_id) {
		this.offer_id = offer_id;
	}
	public String getOffer_description() {
		return offer_description;
	}
	public void setOffer_description(String offer_description) {
		this.offer_description = offer_description;
	}
	public Date getOffer_expiration_date() {
		return offer_expiration_date;
	}
	public void setOffer_expiration_date(Date offer_expiration_date) {
		this.offer_expiration_date = offer_expiration_date;
	}
	
}
