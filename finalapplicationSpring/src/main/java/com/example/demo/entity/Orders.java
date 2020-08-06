package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name="Orders")
public class Orders implements Serializable{
	
	@Id
	@Column(name="idorder")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "orders_sequence")
    @SequenceGenerator(name = "orders_sequence", sequenceName = "orders_sequence")
	private Long idOrder;
	
	
	@Column(name="idclient")
	private Long idClient;
	
	@Column(name="idmanager")
	private String idManager;
	
	
	@Column(name="iddeliveryman")
	private String idDeliveryMan;
	

	@Column(name="dateorders")
	@JsonFormat(pattern="mm-dd-yyyy")
	private Date dateOrder;
	
	
	@Column(name="totalprice")
	private Long totalPrice;
	
	
	

	
	
	
	@PrePersist
	public void onCreate() {
		this.dateOrder = new Date();
	}
	
	
	public Orders() {
		
	}
	
	
	public Orders(Long idOrder, Long idClient, String idManager, String idDeliveryMan, Date dateOrder,
			Long totalPrice) {

		this.idOrder = idOrder;
		this.idClient = idClient;
		this.idManager = idManager;
		this.idDeliveryMan = idDeliveryMan;
		this.dateOrder = dateOrder;
		this.totalPrice = totalPrice;
	}



	public Long getIdOrder() {
		return idOrder;
	}



	public void setIdOrder(Long idOrder) {
		this.idOrder = idOrder;
	}



	public Long getIdClient() {
		return idClient;
	}



	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}



	public String getIdManager() {
		return idManager;
	}



	public void setIdManager(String idManager) {
		this.idManager = idManager;
	}



	public String getIdDeliveryMan() {
		return idDeliveryMan;
	}



	public void setIdDeliveryMan(String idDeliveryMan) {
		this.idDeliveryMan = idDeliveryMan;
	}



	public Date getDateOrder() {
		return dateOrder;
	}



	public void setDateOrder(Date dateOrder) {
		this.dateOrder = dateOrder;
	}



	public Long getTotalPrice() {
		return totalPrice;
	}



	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}

	
	
	
	

}
