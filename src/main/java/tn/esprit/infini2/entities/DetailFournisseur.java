package tn.esprit.infini2.entities;

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
@Table (name= "DetailFournisseur" )
public class DetailFournisseur {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "idDetailFournisseur")
	private Long idDetailFournisseur;
	private String adresse;
	private String matricule;
	@Temporal (TemporalType.DATE)
	private Date dateDebutCollaboration;
	@OneToOne(mappedBy="fournisseurDetail")
	private Fournisseur fournisseur;

}
