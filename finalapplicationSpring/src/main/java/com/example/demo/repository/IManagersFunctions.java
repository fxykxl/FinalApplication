package com.example.demo.repository;



import java.util.List;

import com.example.demo.entity.Managers;

public interface IManagersFunctions {
		
 	public List<Managers> AfficherManager (String email) ;
 	
 	public  List<Managers> list();
 	
	String LoginManager(String email ,String password );
	
	void UpdatePassword(String email, String Oldpassword, String Newpassword);

	void Update(String email, String NewfirstName, String NewLastName, int NewPhone, String NewLocation,
			String NewGender, String NewDateNaissance, String NewDescriptionManager,
			String NewregristrationDate ,String NewProName, String Password);

 	
	void UpdateEmail(String email, String Oldpassword, String Newemail);

	
	void createAManager(Managers manager);
	
}


