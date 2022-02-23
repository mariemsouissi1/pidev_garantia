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
@Table (name= "Operateur" )
public class Operateur {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "idOperateur")
	private Long idOperateur;
	private String nom;
	private String prenom;
	private String password;
	@OneToMany
	private List<Facture> Factures;


}
