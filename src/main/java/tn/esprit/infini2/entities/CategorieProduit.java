package tn.esprit.infini2.entities;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table( name = "CategorieProduit")
public class CategorieProduit {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idCategorieProduit")
	private Long idCategorieProduit; 
	private String codeCategorieProduit;
	private String libelleCategorieProduit;
	@OneToMany(mappedBy="categorieProduits")
	private List<Produit> Produits_Cat;

	
}
