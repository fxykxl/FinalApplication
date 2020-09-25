package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;


@Entity
@Table(name="MenuItems")
public class MenuItems implements Serializable{
	
	@EmbeddedId
	private MenuItemsKey menuItemsKey;
	

	
	
	
	
	
	


}
