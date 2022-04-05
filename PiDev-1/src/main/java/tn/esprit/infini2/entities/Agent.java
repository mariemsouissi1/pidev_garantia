package tn.pidev.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Agent {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAgent;
	
	private int phoneAgent;
	
	private String addressAgent;
	
	@NotNull(message="Can't be null")
	private String FirstName;
	
	@NotNull(message="Can't be null")
	private String LastName;
	
	@NotEmpty
	@Email
	private String Email;
	
	@JsonManagedReference
	@OneToOne
	private Bank bankAgent;

	public Long getIdAgent() {
		return idAgent;
	}

	public void setIdAgent(Long idAgent) {
		this.idAgent = idAgent;
	}

	public int getPhoneAgent() {
		return phoneAgent;
	}

	public void setPhoneAgent(int phoneAgent) {
		this.phoneAgent = phoneAgent;
	}

	public String getAddressAgent() {
		return addressAgent;
	}

	public void setAddressAgent(String addressAgent) {
		this.addressAgent = addressAgent;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}



	public Bank getBankAgent() {
		return bankAgent;
	}

	public void setBankAgent(Bank bankAgent) {
		this.bankAgent = bankAgent;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	

}
