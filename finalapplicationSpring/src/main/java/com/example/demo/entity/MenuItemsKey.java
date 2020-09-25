package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MenuItemsKey implements Serializable {
	
	
	@Column(name="idproduct")
	private Long idProduct;
	
	@Column(name="idmenu")
	private Long idMenu;

	
	
	
	
	public MenuItemsKey() {
		
	}
	
	
	public MenuItemsKey(Long idProduct, Long idMenu) {
		super();
		this.idProduct = idProduct;
		this.idMenu = idMenu;
	}
	
	

	public Long getIdProduct() {
		return idProduct;
	}


	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}


	public Long getIdMenu() {
		return idMenu;
	}


	public void setIdMenu(Long idMenu) {
		this.idMenu = idMenu;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idMenu == null) ? 0 : idMenu.hashCode());
		result = prime * result + ((idProduct == null) ? 0 : idProduct.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuItemsKey other = (MenuItemsKey) obj;
		if (idMenu == null) {
			if (other.idMenu != null)
				return false;
		} else if (!idMenu.equals(other.idMenu))
			return false;
		if (idProduct == null) {
			if (other.idProduct != null)
				return false;
		} else if (!idProduct.equals(other.idProduct))
			return false;
		return true;
	}
	
	
	
	
	

}
