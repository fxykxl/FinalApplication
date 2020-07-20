package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="Managers")
public class Managers implements Serializable{
	
	@Id
	@Column(name="email")
	private String email;
	
	@Column(name="firstname")
	private String firstName;
	
	@Column(name="lastname")
	private String lastName;
	
	@Column(name="proname")
	private String proname;
	
	@Column(name="birthdate")
	private Date birthDate;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="addressmanager")
	private String addressManager;
	
	@Column(name="phone")
	private Long phone;
	
	@Column(name="registrationdate")
	private Date inscriptionDate;
	
	@Column(name="passwordmanager")
	private String passwordManager;
	

	@OneToMany(targetEntity=Organizations.class ,cascade=CascadeType.ALL)
    @JoinColumn(name = "idmanager" , referencedColumnName="email")
	private List<Organizations> organizations;
	
	
	
	
	
	public Managers() {
		
	}
	
	public Managers (String email) {
		this.email=email;
    }
	public Managers(String email, String password , Long phone) {
		
		this.email = email;
		this.passwordManager =password;
		this.phone=phone;
	}





	

	public Managers(String email, String firstName, String lastName, String proname, Date birthDate, String gender,
			String addressManager, Long phone, Date inscriptionDate, String passwordManager,
			List<Organizations> organizations) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.proname = proname;
		this.birthDate = birthDate;
		this.gender = gender;
		this.addressManager = addressManager;
		this.phone = phone;
		this.inscriptionDate = inscriptionDate;
		this.passwordManager = passwordManager;
		this.organizations = organizations;
	}

	public List<Organizations> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(List<Organizations> organizations) {
		this.organizations = organizations;
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

	public String getProname() {
		return proname;
	}

	public void setProname(String proname) {
		this.proname = proname;
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

	public String getAddressManager() {
		return addressManager;
	}

	public void setAddressManager(String addressManager) {
		this.addressManager = addressManager;
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

	public String getPasswordManager() {
		return passwordManager;
	}

	public void setPasswordManager(String passwordManager) {
		this.passwordManager = passwordManager;
	}
	
	
	
	

}
