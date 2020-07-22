package com.example.demo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name="Organizations")
public class Organizations implements Serializable{
	
	@Id
	@Column(name="idorg",updatable=false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "org_sequence")
    @SequenceGenerator(name = "org_sequence", sequenceName = "org_sequence")
	private Long idOrganization;
	
	
	@Column(name="idmanager")
	private String idManager;
	
	@Column(name="label")
	@NotBlank(message="Required Field")
	@Size(min=3, message="Must Be More Than 3 Characters")
	private String label;
	
	@Column(name="addressorganization")
	@NotBlank(message="Required Field")
	@Size(min=10, message="Must Be More Than 10 Characters")
	private String addressOrganization;
	
	@Column(name="description")
	@NotBlank(message="Required Field")
	@Size(min=10, message="Must Be More Than 10 Characters")
	private String description;
	


	
	public Organizations() {
		
	}

	
	public Long getIdOrganization() {
		return idOrganization;
	}

	public void setIdOrganization(Long idOrganization) {
		this.idOrganization = idOrganization;
	}

	public String getIdManager() {
		return idManager;
	}

	public void setIdManager(String idManager) {
		this.idManager = idManager;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getAddressOrganization() {
		return addressOrganization;
	}

	public void setAddressOrganization(String addressOrganization) {
		this.addressOrganization = addressOrganization;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
