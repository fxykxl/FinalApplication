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
	private String idMenu;
	
	
	@Column(name="idorganization")
	private String idOrganization;
	
	@Column(name="description")
	private String description;
	
	
	public Menu() {
		
	}
	
	


	public Menu(String idMenu, String idOrganization, String description) {
		super();
		this.idMenu = idMenu;
		this.idOrganization = idOrganization;
		this.description = description;
	}





	public String getIdMenu() {
		return idMenu;
	}


	public void setIdMenu(String idMenu) {
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
