package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.Clients;



public interface IClientsFunctions {
	
	
 	public List<Clients> AfficherClient (Long phone ) ;
 	
 	public List<Clients> list();
 	
	String LoginClient(String password, Long phone);
	
	void UpdatePassword(Long phone, String Oldpassword, String Newpassword);
	
	//void Update(Long phone, String NewfirstName, String NewLastName, String NewLocation_Clients,
			//String NewEmail, String NewGender, Date NewDate_Naissance, String Password, Date inscreptionDate);
	
	void UpdatePhone(Long phone , String password, String Newphone);


	

 	
	
}


