package tn.pidev.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table
public class LoanSimulation implements Serializable {


	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idLoan")
	private long idLoan;
	
	private double taux;
	
	private double mensuel;

	private double capaciteDeRemboursement;
	
	private double interet;
	
	private double interetTotale;
	
	private double mensualite;
	
	private double principale;
	
	private double montantRembourse;
	
	private double prixImmob;
	
	private double salaire;
	
	private StatutLoanSimulation statusLoanSimulation;
	
	@CreationTimestamp
	private Date publishedDate;

	@ManyToOne
	Bank bankLoan;

	@ManyToOne
	CustomerAccount customerAccountLoan;

	public Long getIdLoan() {
		return idLoan;
	}

	public void setIdLoan(Long idLoan) {
		this.idLoan = idLoan;
	}

	public double getTaux() {
		return taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}

	public double getMensuel() {
		return mensuel;
	}

	public void setMensuel(double mensuel) {
		this.mensuel = mensuel;
	}

	public double getCapaciteDeRemboursement() {
		return capaciteDeRemboursement;
	}

	public void setCapaciteDeRemboursement(double capaciteDeRemboursement) {
		this.capaciteDeRemboursement = capaciteDeRemboursement;
	}

	public double getInteret() {
		return interet;
	}

	public void setInteret(double interet) {
		this.interet = interet;
	}

	public double getInteretTotale() {
		return interetTotale;
	}

	public void setInteretTotale(double interetTotale) {
		this.interetTotale = interetTotale;
	}

	public double getMensualite() {
		return mensualite;
	}

	public void setMensualite(double mensualite) {
		this.mensualite = mensualite;
	}

	public double getPrincipale() {
		return principale;
	}

	public void setPrincipale(double principale) {
		this.principale = principale;
	}

	public double getMontantRembourse() {
		return montantRembourse;
	}

	public void setMontantRembourse(double montantRembourse) {
		this.montantRembourse = montantRembourse;
	}

	public double getPrixImmob() {
		return prixImmob;
	}

	public void setPrixImmob(double prixImmob) {
		this.prixImmob = prixImmob;
	}

	public double getSalaire() {
		return salaire;
	}

	public void setSalaire(double salaire) {
		this.salaire = salaire;
	}

	public StatutLoanSimulation getStatusLoanSimulation() {
		return statusLoanSimulation;
	}

	public void setStatusLoanSimulation(StatutLoanSimulation statusLoanSimulation) {
		this.statusLoanSimulation = statusLoanSimulation;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public Bank getBankLoan() {
		return bankLoan;
	}

	public void setBankLoan(Bank bankLoan) {
		this.bankLoan = bankLoan;
	}

	public CustomerAccount getCustomerAccountLoan() {
		return customerAccountLoan;
	}

	public void setCustomerAccountLoan(CustomerAccount customerAccountLoan) {
		this.customerAccountLoan = customerAccountLoan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	


	

}