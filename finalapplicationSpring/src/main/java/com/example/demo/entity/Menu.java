package com.example.demo.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="Menu")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "idMenu")
public class Menu implements Serializable{
	
	@Id
	@Column(name="idmenu")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "menus_sequence")
    @SequenceGenerator(name = "menus_sequence")
	private Long idMenu;
	
	
	@Column(name="idorganization")
	private Long idOrganization;
	
	@Column(name="description")
	private String descriptionMenu;
	

	@ManyToMany(cascade = { CascadeType.ALL },fetch=FetchType.EAGER)
    @JoinTable(
        name = "Menu_Products", 
        joinColumns = { @JoinColumn(name = "idmenu") }, 
        inverseJoinColumns = { @JoinColumn(name = "idproduct") }
    )
	private List<Products> products ;
	
	

	
	public Menu() {
		
	}
	
	
	

	public Menu(Long idOrganization, String descriptionMenu) {
		super();
		this.idOrganization = idOrganization;
		this.descriptionMenu = descriptionMenu;
	}







	public Long getIdMenu() {
		return idMenu;
	}


	public void setIdMenu(Long idMenu) {
		this.idMenu = idMenu;
	}


	public Long getIdOrganization() {
		return idOrganization;
	}


	public void setIdOrganization(Long idOrganization) {
		this.idOrganization = idOrganization;
	}


	




	public String getDescriptionMenu() {
		return descriptionMenu;
	}




	public void setDescriptionMenu(String descriptionMenu) {
		this.descriptionMenu = descriptionMenu;
	}




	public List<Products> getProducts() {
		return products;
	}




	public void setProducts(List<Products> products) {
		this.products = products;
	}




	

	

}
