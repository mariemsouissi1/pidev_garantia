package tn.esprit.infini2.entities;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column; 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table( name = "Facture")
public class Facture implements Serializable {
 static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idFacture")
	private Long idFacture;
	private float montantRemise;
	private float montantFacture;
	@Temporal (TemporalType.DATE)
	private Date dateCreationFacture;
	private Date dateDerniereModification;
	private Boolean archivee;
	@OneToMany(mappedBy="Factures_R")
	private List<Reglement> Reglements;
	@OneToMany(cascade = CascadeType.ALL, mappedBy="Factures_D")
	private List<DetailFacture> DetailsFactures;
	@ManyToOne
	Fournisseur Fournisseurs_F;
	
}
