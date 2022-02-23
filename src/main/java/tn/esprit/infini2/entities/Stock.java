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
@Table( name="Stock")
public class Stock {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idStock")
	private Long idStock; //
	private Integer qte;
	private Integer qteMin;
	private String libelleStock;
	@OneToMany(mappedBy="Stocks")
	private List<Produit> Produits_S;
	
}
