package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="Admins", uniqueConstraints = { 
		@UniqueConstraint(columnNames = "email") 
	})
public class Admins implements Serializable {
	
	@Id
	@Column(name="email")
	@NotBlank(message="Required Field")
	@Email(message="Please Enter a valid email")
	private String email;
	
	
	
	@Column(name="firstname")
	@NotBlank(message="Required Field")
	@Size(min=3, message="Must Be More Than 3 Characters")
	private String firstName;
	
	
	
	@Column(name="lastname")
	@NotBlank(message="Required Field")
	@Size(min=3, message="Must Be More Than 3 Characters")
	private String lastName;
	
	
	
	@Column(name="birthdate")
	@JsonFormat(pattern="mm-dd-yyyy")
	private Date birthDate;
	
	
	
	@Column(name="gender")
	private String gender;
	
	
	
	@Column(name="addressadmin")
	@NotBlank(message="Required Field")
	@Size(min=10 ,message="Must be more than 10 characters")
	private String addressAdmin;
	
	
	
	@Column(name="phone")
	@Min(10) @NotNull(message="Required Field")
	private Long phone;
	
	
	
	@Column(name="registrationdate",updatable=false)
	@JsonFormat(pattern="mm-dd-yyyy")
	private Date inscriptionDate;
	
	
	
	@Column(name="paswordadmin")
	@NotBlank(message="Required Field")
	@Size(min=8 , message="Must be More Than 8 characters")
	private String passwordAdmin;
	
	
	
	public Admins() {
		
	}
	
    public Admins(String email) {
		
		this.email=email;
	}
	
	public Admins(String email, String passwordadmin ) {
		this.email = email;
		this.passwordAdmin= passwordadmin ;
		
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
