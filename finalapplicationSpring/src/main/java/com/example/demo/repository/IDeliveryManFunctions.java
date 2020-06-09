package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.DeliveryMen;



public interface IDeliveryManFunctions {


 	public List<DeliveryMen> AfficherDeliveryMan (String email) ;
 	
 	public List<DeliveryMen> list();
 	
 	public void InsertDeliveryMan (DeliveryMen d) ;
 	
	String LoginDeliveryMan(String email , String password);
	
	void UpdatePassword(String email, String Oldpassword, String Newpassword);
	
	void Update(String email, String NewfirstName, String NewLastName, int NewPhone, String NewLocation_DeliveryMan,
			 String NewGender, String NewDate_Naissance, String Password_DeliveryMan, String inscreptionDate);

	void UpdateEmail(String email, String password, String Newemail);
 	
}
