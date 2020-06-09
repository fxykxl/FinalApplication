package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.Admins;
import com.example.demo.entity.Clients;
import com.example.demo.entity.DeliveryMen;
import com.example.demo.entity.Managers;



public interface IAdminsFunctions {
	
	
 	public List<Admins> AfficherAdmin (String email) ;
 	
 	public List<Admins> list();
 	
	String LoginAdmin(String email,String password);
	
    void UpdatePassword(String email, String Oldpassword, String Newpassword);
	
	void Update(int Newphone, String NewfirstName, String NewLastName, int NewPhone, String NewLocation,
			String email, String NewGender, String NewDate_Naissance, String Password, String NewregistrationDate);
	
	void UpdateEmail(String email, String password, String Newemail);

	void InsertAdmin(Admins a);
	
	public void  DeleteClient (Clients c);
	
	public void  DeleteManager (Managers m);
	
	public void  DeleteDeliveryMan (DeliveryMen d);
	
	public void  DeleteAdmin (Admins a);

    public void  DeleteAllClient ();
	
	public void  DeleteAllManager ();
	
	public void  DeleteAllDeliveryMan ();
	
	public void  DeleteAllAdmin ();
 	
	
}


