package tn.esprit.infini2.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity 
@Table (name= "Reglement" )
public class Reglement {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "idReglement")
	private Long idReglement;
	private float montantPaye;
	private float montantRestant;
	private Boolean payee;
	@Temporal (TemporalType.DATE)
	private Date dateReglement;
	@ManyToOne
	Facture Factures_R;

}