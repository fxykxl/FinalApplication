package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Products")
public class Products {
	
	@Id
	@Column(name="idproduct")
	private String idProduct;
	
	@Column(name="labelproduct")
	private String label;
	
	@Column(name="priceproduct")
	private String priceProduct;
	
	@Column(name="descriptionproduct")
	private String descriptionProduct;
	
	public Products() {
		
	}

	public Products(String idProduct, String label, String priceProduct, String descriptionProduct) {
		super();
		this.idProduct = idProduct;
		this.label = label;
		this.priceProduct = priceProduct;
		this.descriptionProduct = descriptionProduct;
	}

	public String getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(String idProduct) {
		this.idProduct = idProduct;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getPriceProduct() {
		return priceProduct;
	}

	public void setPriceProduct(String priceProduct) {
		this.priceProduct = priceProduct;
	}

	public String getDescriptionProduct() {
		return descriptionProduct;
	}

	public void setDescriptionProduct(String descriptionProduct) {
		this.descriptionProduct = descriptionProduct;
	}
	
	

}
