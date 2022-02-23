package tn.esprit.infini2.entities;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity 
@Table (name= "SecteurActivite" )
public class SecteurActivite { 	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "idSecteurActivite")
	private Long idSecteurActivite;
	private String codeSecteurActivite;
	private String libelleSecteurActivite;
	@ManyToMany
	private List<Fournisseur> Fournisseurs_S;
	
}
