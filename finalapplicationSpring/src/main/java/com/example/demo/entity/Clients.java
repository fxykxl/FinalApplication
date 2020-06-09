package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="Clients")
public class Clients {
	
	@Id
	@Column(name="phone")
	private Long phone;
	
	@Column(name="firstname")
	private String firstName;
	
	@Column(name="lastname")
	private String lastName;
	
	@Column(name="birthdate")
	private Date birthDate;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="gender")
	private String email;
	
	@Column(name="addressclient")
	private String addressClient;
	
	@Column(name="inscriptiondate")
	private Date inscriptionDate;
	
	@Column(name="passwordclient")
	private String passwordClient;
	
	public Clients() {
		
	}
	
	
	
	public Clients(Long phone, String firstName, String lastName, Date birthDate, String gender, String email,
			String addressClient, Date inscriptionDate, String passwordClient) {
		super();
		this.phone = phone;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.gender = gender;
		this.email = email;
		this.addressClient = addressClient;
		this.inscriptionDate = inscriptionDate;
		this.passwordClient = passwordClient;
	}







	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
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
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getAddressClient() {
		return addressClient;
	}
	public void setAddressClient(String addressClient) {
		this.addressClient = addressClient;
	}
	
	
	public Date getInscriptionDate() {
		return inscriptionDate;
	}
	public void setInscriptionDate(Date inscriptionDate) {
		this.inscriptionDate = inscriptionDate;
	}
	
	
	public String getPasswordClient() {
		return passwordClient;
	}
	public void setPasswordClient(String passwordClient) {
		this.passwordClient = passwordClient;
	}
	
	
	
	

	

}
