package tn.esprit.infini2.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="customer")
public class Customer implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idCustomer")
	private Long idCustomer; //clé primaire

	@Column(name = "first_name", nullable = false, length = 20)
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 20)
	private String lastName;

	@Temporal(TemporalType.DATE)
	private Date birthDate;

	private int phoneNumber;

	private int cin;

	@Column(name = "gender", nullable = false, length = 10)
	private Gender gender;

	private float monthlyIncome;

	private String job;

	@Enumerated(EnumType.STRING)
	private Governorates governorate;


	public Governorates getGovernorate() {
		return governorate;
	}

	public void setGovernorate(Governorates governorate) {
		this.governorate = governorate;
	}

	@Column
	private Boolean active=false;

	@Column(name="email",nullable = false,unique = true, length = 45)
	private String email;

	@Column(nullable = false, length = 64)
	private String password;

	public float getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(float monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	@OneToOne
	private customerAccount customerAccount;

	public Long getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(Long idCustomer) {
		this.idCustomer = idCustomer;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getCin() {
		return cin;
	}

	public void setCin(int cin) {
		this.cin = cin;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public customerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(customerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}


	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}


	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
}
