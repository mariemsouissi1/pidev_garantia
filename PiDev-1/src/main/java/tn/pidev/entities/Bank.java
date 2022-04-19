package tn.pidev.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Bank {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idBank")
	private long idBank; 
	
	public String nameBank;
	
	public String descriptionBank;

	public String adresseBank;
	
	private float tauxMoyenDuMarche;

	public float margeInteretBank;
	
	
	@JsonBackReference
	@ManyToOne
	Agent agentBank;
	
	@JsonIgnore
	@OneToMany(mappedBy = "bankLoan")
	private List<LoanSimulation> LoanSimulatorListBank;

	public long getIdBank() {
		return idBank;
	}

	public void setIdBank(long idBank) {
		this.idBank = idBank;
	}

	public String getNameBank() {
		return nameBank;
	}

	public void setNameBank(String nameBank) {
		this.nameBank = nameBank;
	}

	public String getDescriptionBank() {
		return descriptionBank;
	}

	public void setDescriptionBank(String descriptionBank) {
		this.descriptionBank = descriptionBank;
	}

	public String getAdresseBank() {
		return adresseBank;
	}

	public void setAdresseBank(String adresseBank) {
		this.adresseBank = adresseBank;
	}

	public float getTauxMoyenDuMarche() {
		return tauxMoyenDuMarche;
	}

	public void setTauxMoyenDuMarche(float tauxMoyenDuMarche) {
		this.tauxMoyenDuMarche = tauxMoyenDuMarche;
	}


	public float getMargeInteretBank() {
		return margeInteretBank;
	}

	public void setMargeInteretBank(float margeInteretBank) {
		this.margeInteretBank = margeInteretBank;
	}


	public Agent getAgentBank() {
		return agentBank;
	}

	public void setAgentBank(Agent agentBank) {
		this.agentBank = agentBank;
	}

	public List<LoanSimulation> getLoanSimulatorListBank() {
		return LoanSimulatorListBank;
	}

	public void setLoanSimulatorListBank(List<LoanSimulation> loanSimulatorListBank) {
		LoanSimulatorListBank = loanSimulatorListBank;
	}

	
	

}
