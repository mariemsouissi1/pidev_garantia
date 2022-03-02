package tn.esprit.infini2.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="Claim")
public class Claim  implements Serializable {
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
	private Claim_state claim_state;
	@Enumerated(EnumType.STRING)
	private Claim_type claim_type;
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
	public void setClaim_state(Claim_state claim_state) {
		this.claim_state = claim_state;
	}
	public Claim_type getClaim_type() {
		return claim_type;
	}
	public void setClaim_type(Claim_type claim_type) {
		this.claim_type = claim_type;
	}
	
}


