package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="Deliveryman")
public class DeliveryMen implements Serializable{
	
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
	@Size(min=3, message="Must Be More Than 3 Characters")
	@NotBlank(message="Required Field")
	private String lastName;
	
	
	
	@Column(name="birthdate")
	@JsonFormat(pattern="mm-dd-yyyy")
	private Date birthDate;
	
	
	
	@Column(name="gender")
	private String gender;
	
	
	
	@Column(name="phone")
	@Min(10) @NotNull(message="Required Field")
	private Long phone;
	
	
	
	@Column(name="addressdeliveryman")
	@NotBlank(message="Required Field")
	@Size(min=10 ,message="Must be more than 10 characters")
	private String addressDeliveryMan;
	
	
	
	@Column(name="registrationdate" , updatable=false)
	@JsonFormat(pattern="mm-dd-yyyy")
	private Date inscriptionDate;
	
	
	
	@Column(name="passworddeliveryman")
	@NotBlank(message="Required Field")
	@Size(min=8 , message="Must be More Than 8 characters")
	private String passwordDeliverMan;
	
	
	@OneToMany(targetEntity=Orders.class ,cascade=CascadeType.ALL ,fetch= FetchType.LAZY)
    @JoinColumn(name = "iddeliveryman" , referencedColumnName="email")
	private List<Orders> orders;
	
		
	@PrePersist
	public void onCreate() {
		this.inscriptionDate=new Date();
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

	public String getPasswordDeliverMan() {
		return passwordDeliverMan;
	}

	public void setPasswordDeliverMan(String passwordDeliverMan) {
		this.passwordDeliverMan = passwordDeliverMan;
	}
	
	
	

}
