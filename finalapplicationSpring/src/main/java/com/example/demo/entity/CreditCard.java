package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Creditcard")
public class CreditCard implements Serializable{
	
	@Id
	@Column(name="num")
	private Long number;
	
	@Column(name="cvv")
	private int cvv;
	
	@Column(name="holdername")
	private String holdername;
	
	
	public CreditCard() {
		
	}

	public CreditCard(Long number, int cvv, String holdername) {
		super();
		this.number = number;
		this.cvv = cvv;
		this.holdername = holdername;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public String getHoldername() {
		return holdername;
	}

	public void setHoldername(String holdername) {
		this.holdername = holdername;
	}
	
	

}
