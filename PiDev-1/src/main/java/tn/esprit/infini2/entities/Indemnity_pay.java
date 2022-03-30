package tn.esprit.infini2.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity(name ="INDEMNITY_Indemnity_pay")
@Table(name ="INDEMNITY_Indemnity_pay")
public class Indemnity_pay implements Serializable{
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getIndemnity_payCode() {
		return Indemnity_payCode;
	}
	public Date getIndemnity_payDate() {
		return Indemnity_payDate;
	}
	public double getAmountPayed() {
		return amountPayed;
	}
	public String getIndemnity_payMethod() {
		return Indemnity_payMethod;
	}
	public Contract getContract() {
		return contract;
	}
	public void setIndemnity_payCode(String indemnity_payCode) {
		Indemnity_payCode = indemnity_payCode;
	}
	public void setIndemnity_payDate(Date indemnity_payDate) {
		Indemnity_payDate = indemnity_payDate;
	}
	public void setAmountPayed(double amountPayed) {
		this.amountPayed = amountPayed;
	}
	public void setIndemnity_payMethod(String indemnity_payMethod) {
		Indemnity_payMethod = indemnity_payMethod;
	}
	public void setContract(Contract contract) {
		this.contract = contract;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="Indemnity_pay_ID")
	private int id; // primary key
	
	@Column(name="Indemnity_payCode")
	private String Indemnity_payCode;
	
	@Temporal (TemporalType.DATE)
	private Date Indemnity_payDate;
	
	@Column(name="amountPayed")
	private double amountPayed;
	
	@Column(name="Indemnity_payMethod")
	private String Indemnity_payMethod;
	

	@ManyToOne
	Contract contract;
	
	@OneToOne(mappedBy="indemnity_pay")
	private Sinister sinister;
	@ManyToOne
	customerAccount  customer_account;
	public char[] getId() {
		// TODO Auto-generated method stub
		return null;
	}
	public void save(Indemnity_pay ip) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
