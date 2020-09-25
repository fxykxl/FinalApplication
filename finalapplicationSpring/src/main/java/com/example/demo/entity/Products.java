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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="Products")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "idProduct")
public class Products implements Serializable{
	
	@Id
	@Column(name="idproduct")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "products_sequence")
    @SequenceGenerator(name = "products_sequence")
	private Long idProduct;
	
	@Column(name="labelproduct")
	private String label;
	
	@Column(name="priceproduct")
	private int priceProduct;
	
	@Column(name="descriptionproduct")
	private String descriptionProduct;
	
	
	
	@ManyToMany(mappedBy = "products" )
	private List<Menu> menus;
	
	
	 @JsonIgnore
	 @ManyToMany(fetch = FetchType.LAZY,
	            cascade = {
	                CascadeType.PERSIST,
	                CascadeType.MERGE
	            },mappedBy = "products_orders" )
	private Set<Orders> orders=new HashSet<>();
	
	
	public Products() {
		
	}

	public Products(String label, int priceProduct, String descriptionProduct) {
		this.label = label;
		this.priceProduct = priceProduct;
		this.descriptionProduct = descriptionProduct;
	}

	public Long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getPriceProduct() {
		return priceProduct;
	}

	public void setPriceProduct(int priceProduct) {
		this.priceProduct = priceProduct;
	}

	public String getDescriptionProduct() {
		return descriptionProduct;
	}

	public void setDescriptionProduct(String descriptionProduct) {
		this.descriptionProduct = descriptionProduct;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public Set<Orders> getOrders() {
		return orders;
	}

	public void setOrders(Set<Orders> orders) {
		this.orders = orders;
	}

	
	
	

}
