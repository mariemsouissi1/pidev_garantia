package tn.esprit.infini2.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="employee")
public class Employee implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idEmployee")
	private Long idEmployee; //clé primaire

	@Column(name = "first_name", nullable = false, length = 20)
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 20)
	private String lastName;

	@Temporal(TemporalType.DATE)
	private Date birthDate;

	private int phoneNumber;

	private int cin;


	@Column(nullable = false, name="email", unique = true, length = 45)
	private String email;

	@Column(nullable = true	, length = 64)
	private String password;

	@Enumerated(EnumType.STRING)
	private employeeCategory employeeCategorys;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<CustomerAccount> customerAccounts;

	public Long getIdEmployee()
	{
		return idEmployee;
	}

	public void setIdEmployee(Long idEmployee)
	{
		this.idEmployee = idEmployee;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public Date getBirthDate()
	{
		return birthDate;
	}

	public void setBirthDate(Date birthDate)
	{
		this.birthDate = birthDate;
	}

	public int getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public int getCin()
	{
		return cin;
	}

	public void setCin(int cin)
	{
		this.cin = cin;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public employeeCategory getEmployeeCategorys()
	{
		return employeeCategorys;
	}

	public void setEmployeeCategorys(employeeCategory employeeCategorys) {
		this.employeeCategorys = employeeCategorys;
	}

	public Set<CustomerAccount> getCustomerAccounts() {
		return customerAccounts;
	}

	public void setCustomerAccounts(Set<CustomerAccount> customerAccounts) {
		this.customerAccounts = customerAccounts;
	}


}
