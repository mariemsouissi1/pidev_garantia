package tn.esprit.infini2.entities;
import javax.persistence.Column; 
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table( name = "DetailFacture")
public class DetailFacture {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idDetailFacture")
	private Long idDetailFacture; 
	private Integer qteCommandee;
	private float prixTotalDetaim;
	private Integer pourcentageRemise;
	private float montantRemise;
	@ManyToOne
	Facture Factures_D;
	@ManyToOne
	Produit Produits_Fac;

}
