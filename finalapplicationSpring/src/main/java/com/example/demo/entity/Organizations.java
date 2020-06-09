package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Organizations")
public class Organizations {
	
	@Id
	@Column(name="idorg")
	private String idOrganization;
	
	@Column(name="idmanager")
	private String idManager;
	
	@Column(name="label")
	private String label;
	
	@Column(name="addressorganization")
	private String addressOrganization;
	
	@Column(name="description")
	private String description;
	
	public Organizations() {
		
	}

	public Organizations(String idOrganization, String idManager, String label, String addressOrganization,
			String description) {
		super();
		this.idOrganization = idOrganization;
		this.idManager = idManager;
		this.label = label;
		this.addressOrganization = addressOrganization;
		this.description = description;
	}

	public String getIdOrganization() {
		return idOrganization;
	}

	public void setIdOrganization(String idOrganization) {
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
