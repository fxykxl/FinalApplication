package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Admins")
public class Admins {
	
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
	
	@Column(name="addressadmin")
	private String addressAdmin;
	
	@Column(name="phone")
	private Long phone;
	
	@Column(name="registrationdate")
	private Date inscriptionDate;
	
	@Column(name="paswordadmin")
	private String passwordAdmin;
	
	
	
	public Admins() {
		
	}

	public Admins(String email, String firstName, String lastName, Date birthDate, String gender, String addressAdmin,
			Long phone, Date inscriptionDate, String passwordAdmin) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.gender = gender;
		this.addressAdmin = addressAdmin;
		this.phone = phone;
		this.inscriptionDate = inscriptionDate;
		this.passwordAdmin = passwordAdmin;
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

	public String getAddressAdmin() {
		return addressAdmin;
	}

	public void setAddressAdmin(String addressAdmin) {
		this.addressAdmin = addressAdmin;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public Date getInscriptionDate() {
		return inscriptionDate;
	}

	public void setInscriptionDate(Date inscriptionDate) {
		this.inscriptionDate = inscriptionDate;
	}

	public String getPasswordAdmin() {
		return passwordAdmin;
	}

	public void setPasswordAdmin(String passwordAdmin) {
		this.passwordAdmin = passwordAdmin;
	}
	
	
	
	

}
