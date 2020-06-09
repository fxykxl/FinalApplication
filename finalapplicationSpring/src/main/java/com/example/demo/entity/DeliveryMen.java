package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DeliveryMan")
public class DeliveryMen {
	
	@Id
	@Column(name="email")
	private String email;	
	
	@Column(name="firstname")
	private String firstName;
	
	@Column(name="lastname")
	private String lastName;
	
	@Column(name="birthdate")
	private Date birthDate;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="addressdeliveryman")
	private String addressDeliveryMan;
	
	@Column(name="registrationdate")
	private Date inscriptionDate;
	
	@Column(name="passworddeliveryman")
	private String passwordDeliverMan;
	
	
	public DeliveryMen() {
		
	}

	public DeliveryMen(String email, String firstName, String lastName, Date birthDate, String gender,
			String addressDeliveryMan, Date inscriptionDate, String passwordDeliverMan) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.gender = gender;
		this.addressDeliveryMan = addressDeliveryMan;
		this.inscriptionDate = inscriptionDate;
		this.passwordDeliverMan = passwordDeliverMan;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddressDeliveryMan() {
		return addressDeliveryMan;
	}

	public void setAddressDeliveryMan(String addressDeliveryMan) {
		this.addressDeliveryMan = addressDeliveryMan;
	}

	public Date getInscriptionDate() {
		return inscriptionDate;
	}

	public void setInscriptionDate(Date inscriptionDate) {
		this.inscriptionDate = inscriptionDate;
	}

	public String getPasswordDeliverMan() {
		return passwordDeliverMan;
	}

	public void setPasswordDeliverMan(String passwordDeliverMan) {
		this.passwordDeliverMan = passwordDeliverMan;
	}
	
	
	

}
