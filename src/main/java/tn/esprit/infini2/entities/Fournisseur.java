package tn.esprit.infini2.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity 
@Table (name= "Fournisseur" )
public class Fournisseur implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "idFournisseur")
	private Long idFournisseur;
	private String codeFournisseur;
	private String libelleFournisseur;
	
	@Enumerated(EnumType.STRING)
	private CategorieFournisseur categorieFournisseur;
	@OneToMany(mappedBy="Fournisseurs_F")
	private List<Facture> Factures_F;
	@ManyToMany
	private List<SecteurActivite> SecteursActivite;
	@OneToOne
	private DetailFournisseur fournisseurDetail;
	

}
