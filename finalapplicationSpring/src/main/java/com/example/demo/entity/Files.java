package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Files")
public class Files {
	
	@Id	
	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String idFile;
	
	@Column(name="name")
	private String name;
	
	@Column(name="type")
	private String type;
	
	@Lob
	@Column(name="data")
    private byte[] data;
	
	

	public Files(String name, String type, byte[] data) {
		this.name = name;
		this.type = type;
		this.data = data;
	}

	public String getIdFile() {
		return idFile;
	}

	public void setIdFile(String idFile) {
		this.idFile = idFile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
	
	
	
	

}
