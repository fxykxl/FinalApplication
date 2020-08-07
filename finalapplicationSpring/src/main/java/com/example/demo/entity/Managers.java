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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="Managers", uniqueConstraints = { 
		@UniqueConstraint(columnNames = "email") 
	})
public class Managers implements Serializable{
	
	@Id
	@Column(name="email" , updatable=false)
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
	
	@Column(name="proname")
	@NotBlank(message="Required Field")
	@Size(min=3, message="Must Be More Than 3 Characters")
	private String proname;
	
	@Column(name="birthdate")
	@JsonFormat(pattern="mm-dd-yyyy")
	private Date birthDate;
	
	
	
	@Column(name="gender")
	private String gender;
	
	
	
	@Column(name="addressmanager")
	@NotBlank(message="Required Field")
	@Size(min=10 ,message="Must be more than 10 characters")
	private String addressManager;
	
	
	@Min(10) @NotNull(message="Required Field")
	@Column(name="phone",updatable=false)
	private Long phone;
	
	
	
	@Column(name="registrationdate")
	@JsonFormat(pattern="mm-dd-yyyy")
	private Date inscriptionDate;
	
	
	
	@Column(name="passwordmanager")
	@NotBlank(message="Required Field")
	@Size(min=8 , message="Must be More Than 8 characters")
	private String passwordManager;
	
	
	@Column(name="accountstatus")
	private String accountStatus;
	

	@OneToMany(targetEntity=Organizations.class ,cascade=CascadeType.ALL ,fetch= FetchType.LAZY)
    @JoinColumn(name = "idmanager" , referencedColumnName="email")
	private List<Organizations> organizations;
	
	@OneToMany(targetEntity=Orders.class ,cascade=CascadeType.ALL ,fetch= FetchType.LAZY)
    @JoinColumn(name = "idmanager" , referencedColumnName="email")
	private List<Orders> orders;
	
	
	
	
	public Managers() {
		
	}
	
	
	@PrePersist
	public void onCreate() {
		this.inscriptionDate=new Date();
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


	public String getAccountStatus() {
		return accountStatus;
	}


	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}


	public List<Orders> getOrders() {
		return orders;
	}


	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}
	
	
	
	

}
