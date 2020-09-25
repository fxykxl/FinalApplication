package com.example.demo.entity;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity
@Table(name="Helper")
public class Helper {
	
	@Id
	@Column(name="idhelper")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "helper_sequence")
    @SequenceGenerator(name = "helper_sequence")
	private Long idHelper;
	
	
	@Column(name="clientfullname")
	private String clientFullName;
	
	@Column(name="clientlocation")
	private String clientLocation;
	
	
	@Column(name="clientphone")
	private Long clientPhone;
	
	
	
	@OneToMany(targetEntity=Products.class ,cascade=CascadeType.ALL ,fetch= FetchType.LAZY)
    @JoinColumn(name = "idproduct" )
	private List<Products> products;

	public Long getIdHelper() {
		return idHelper;
	}

	public void setIdHelper(Long idHelper) {
		this.idHelper = idHelper;
	}

	public String getClientFullName() {
		return clientFullName;
	}

	public void setClientFullName(String clientFullName) {
		this.clientFullName = clientFullName;
	}

	public String getClientLocation() {
		return clientLocation;
	}

	public void setClientLocation(String clientLocation) {
		this.clientLocation = clientLocation;
	}

	public Long getClientPhone() {
		return clientPhone;
	}

	public void setClientPhone(Long clientPhone) {
		this.clientPhone = clientPhone;
	}



	public List<Products> getProducts() {
		return products;
	}

	public void setProducts(List<Products> products) {
		this.products = products;
	}
	
	


}