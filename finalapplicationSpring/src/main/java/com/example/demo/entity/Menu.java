package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Menu")
public class Menu implements Serializable{
	
	@Id
	@Column(name="idmenu")
	private Long idMenu;
	
	
	@Column(name="idorganization")
	private String idOrganization;
	
	@Column(name="description")
	private String description;
	
	
	public Menu() {
		
	}
	
	


	public Menu(Long idMenu, String idOrganization, String description) {
		super();
		this.idMenu = idMenu;
		this.idOrganization = idOrganization;
		this.description = description;
	}





	public Long getIdMenu() {
		return idMenu;
	}


	public void setIdMenu(Long idMenu) {
		this.idMenu = idMenu;
	}


	public String getIdOrganization() {
		return idOrganization;
	}


	public void setIdOrganization(String idOrganization) {
		this.idOrganization = idOrganization;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
