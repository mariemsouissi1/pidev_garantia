package tn.esprit.infini2.entities;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

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
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date offer_expiration_date;
	@Enumerated(EnumType.STRING)
	private Offer_categorie offer_categorie;
	private Boolean Offer_visibility;
	private Boolean expired;
	private float price;

	@ManyToMany (mappedBy="offers")
	private List<CustomerAccount> O_customerAccount;
	
	
	


	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Boolean getExpired() {
		return expired;
	}
	public void setExpired(Boolean expired) {
		this.expired = expired;
	}

	public Boolean getOffer_visibility() {
		return Offer_visibility;
	}
	public void setOffer_visibility(Boolean offer_visibility) {
		Offer_visibility = offer_visibility;
	}
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
	

	public Offer_categorie getOffer_categorie() {
		return offer_categorie;
	}
	public void setOffer_categorie(Offer_categorie offer_categorie) {
		this.offer_categorie = offer_categorie;
	}
	public List<CustomerAccount> getO_customerAccount() {
		return O_customerAccount;
	}
	public void setO_customerAccount(List<CustomerAccount> o_customerAccount) {
		O_customerAccount = o_customerAccount;
	}
	
}
