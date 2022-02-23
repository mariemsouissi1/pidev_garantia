package tn.esprit.infini2.entities;

import java.util.Date;
import java.util.List;

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
@Table( name = "Produit")
public class Produit {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idProduit")
	private Long idProduit; 
	private String codeProduit;
	private String libelleProduit;
	private float prix;
	@Temporal (TemporalType.DATE)
	private Date dateCreation;
	private Date dateDerniereModification;
	@OneToMany(mappedBy="Produits_Fac")
	private List<DetailFacture> DetailsFactures_Produit;
	@ManyToOne
	Stock Stocks;
	@ManyToOne
	CategorieProduit categorieProduits;
	
}
