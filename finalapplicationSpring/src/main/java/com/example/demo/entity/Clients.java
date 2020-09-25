package com.example.demo.entity;


import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="Clients", uniqueConstraints = { 
		@UniqueConstraint(columnNames = "phone"),
		@UniqueConstraint(columnNames = "email") 
	})
public class Clients implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="phone")
	@Min(10) @NotNull(message="Required Field")
	private Long phone;
	
	
	@Column(name="firstname")
	@NotBlank(message="Required Field")
	@Size(min=3, message="Must Be More Than 3 Characters")
	private String firstName;
	
	@Size(min=3, message="Must Be More Than 3 Characters")
	@NotBlank(message="Required Field")
	@Column(name="lastname")
	private String lastName;
	
	

	@JsonFormat(pattern="mm-dd-yyyy")
	@Column(name="birthdate")
	private Date birthDate;
	
	
	
	@NotBlank(message="Required Field")
	@Email(message="Please Enter a valid email")
	@Column(name="email")
	private String email;
	
	
	@NotBlank(message="Required Field")
	@Size(min=10 ,message="Must be more than 10 characters")
	@Column(name="addressclient")
	private String addressClient;
	
	
	@JsonFormat(pattern="mm-dd-yyyy")
	@Column(name="inscriptiondate" , updatable=false)
	private Date inscriptionDate;
	
	
	@NotBlank(message="Required Field")
	@Size(min=8 , message="Must be More Than 8 characters")
	@Column(name="passwordclient")
	private String passwordClient;
	
	@Column(name="isloggedin")
	private String isLoggedIn;
	
	@Transient
	@JsonIgnore
	private String confirmPassword;
	
	@OneToMany(targetEntity=Orders.class ,cascade=CascadeType.ALL ,fetch= FetchType.LAZY)
    @JoinColumn(name = "idclient" , referencedColumnName="phone")
	private List<Orders> orders;
	
	
	@PrePersist
	public void onCreate() {
		this.inscriptionDate = new Date();
	}
	
	
	public Clients() {
		
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

	

	public String getIsLoggedIn() {
		return isLoggedIn;
	}


	public void setIsLoggedIn(String isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}


	public String getConfirmPassword() {
		return confirmPassword;
	}


	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}


	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	@JsonIgnore
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	@JsonIgnore
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	@JsonIgnore
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	

	

}
