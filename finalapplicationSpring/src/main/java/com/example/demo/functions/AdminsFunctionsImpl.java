package com.example.demo.functions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Admins;
import com.example.demo.entity.Clients;
import com.example.demo.entity.DeliveryMen;
import com.example.demo.entity.Managers;
import com.example.demo.repository.AdminsRepository;
import com.example.demo.repository.ClientsRepository;
import com.example.demo.repository.DeliveryManRepository;
import com.example.demo.repository.IAdminsFunctions;
import com.example.demo.repository.ManagersRepository;



@Service
@Transactional
public class AdminsFunctionsImpl implements IAdminsFunctions {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Autowired
	AdminsRepository adminrepository ;
	
	@Autowired
	DeliveryManRepository deliverymanrepository;
	
	@Autowired
	ManagersRepository managerrepository;
	
	@Autowired
	ClientsRepository clientsrepository;
	
    @Autowired
    ClientsFunctionsImpl clientsfunctionsimpl;
    
    @Autowired
	ManagersFunctionsImpl managersfunctionsimpl ;
	  
	@Autowired
	private DeliveryManFunctionsImpl deliverymanfunctionsimpl;
	
	@Override
	public List<Admins> AfficherAdmin(String email) {

		String sql ="select * from ADMINS where EMAIL='"+email+"'";
		List<Admins> ListAdmins = jdbcTemplate.query(sql ,
				BeanPropertyRowMapper.newInstance(Admins.class));
		
		return ListAdmins;
	}

	@Override
	public List<Admins> list() {
		String sql ="select * from Admins";
		List<Admins> ListAdmins = jdbcTemplate. query(sql ,
				BeanPropertyRowMapper.newInstance(Admins.class));
		
		return ListAdmins;
	}
	
	@Override
	public void InsertAdmin (Admins a) {
		
		if(AfficherAdmin(a.getEmail().toString()).size() != 0)
		{
			System.out.println("Email already exist");
		}
		
		else {
			adminrepository.save(a);
		} 
		
	}

	@Override
	public String LoginAdmin(String email, String password) {
	
		
		try {
			if (AfficherAdmin(email).get(0).getPasswordAdmin().toString().equals(new MD5().encrypt(password))) 
				
			{
				return "connected";
		    }	
			
			else return "Email or password incorrect";
			} catch (Exception e) {
				
				e.printStackTrace();
			return "erreur de hashage du mot de passe";
			}
	}

	@Override
	public void UpdatePassword(String email, String Oldpassword, String Newpassword) {
		
		try {
			if (AfficherAdmin(email).get(0).getPasswordAdmin().toString().equals(new MD5().encrypt(Oldpassword)))
			{
				String sql = "update Admins set PASSWORDADMIN= '"+new MD5().encrypt(Newpassword)+"' where EMAIL= '"+email +"'";
				jdbcTemplate.execute(sql);
				
			}
			
			else {
				System.out.println("Oldpassword incorrect");
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}

	@Override
	public void Update(int Newphone, String NewfirstName, String NewLastName, int NewPhone, String NewLocation,
			String email, String NewGender, String NewDate_Naissance, String Password, String NewregistrationDate) {
		
		try {
			if (AfficherAdmin(email).get(0).getPasswordAdmin().toString().equals(new MD5().encrypt(Password))) 
			{
				String sql ="update Admins set FIRSTNAME='"+NewfirstName+"' ,LASTNAME='"+NewLastName+"' ,PHONE="+NewPhone+" ,"
						+ "ADRESSADMIN='"+NewLocation+"'  ,GENDER='"+NewGender+"' ,"+ 
						"BIRTHDATE='"+NewDate_Naissance+"' REGISTRATIONDATE='"+NewregistrationDate+"' where EMAIL="+email;
				jdbcTemplate.execute(sql);
			}
			else {
				System.out.println("Password incorrect");
			}
		} catch (DataAccessException e) {
		
			e.printStackTrace();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void UpdateEmail(String email, String password, String Newemail) {
		
		try {
			if (AfficherAdmin(email).get(0).getPasswordAdmin().toString().equals(new MD5().encrypt(password)))
			{
				String sql = "update Admins set EMAIL= '"+Newemail+"' where EMAIL= '"+email +"'";
				jdbcTemplate.execute(sql);
				
				 
			}
			
			else {
				System.out.println("Password incorrect");
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void DeleteClient(Clients c) {
		

		if(clientsfunctionsimpl.AfficherClient((c.getPhone())).size() == 0)
		{
			System.out.println("Clients not existe");
		}
		
		else {
			clientsrepository.delete(c);
		} 
		
	 }

	@Override
	public void DeleteManager(Managers m) {

		if(managersfunctionsimpl.AfficherManager(m.getEmail().toString()).size() == 0)
		{
			System.out.println("Managers not existe");
		}
		
		else {
			managerrepository.delete(m);
		} 
		
		
	}

	@Override
	public void DeleteDeliveryMan(DeliveryMen d) {
		
		if(deliverymanfunctionsimpl.AfficherDeliveryMan(d.getEmail().toString()).size() == 0)
		{
			System.out.println("DeliveryMan not existe");
		}
		
		else {
			deliverymanrepository.delete(d);
		} 
		
		
	}

	@Override
	public void DeleteAdmin(Admins a ) {
		
		if(AfficherAdmin(a.getEmail().toString()).size() == 0)
		{
			System.out.println("Admin not existe");
		}
		
		else {
			adminrepository.delete(a);
		} 
		
		
	}

	@Override
	public void DeleteAllClient() {
		
		clientsrepository.deleteAll();
	}

	@Override
	public void DeleteAllManager() {
		
		managerrepository.deleteAll();
	}

	@Override
	public void DeleteAllDeliveryMan() {
		
		deliverymanrepository.deleteAll();
	}

	@Override
	public void DeleteAllAdmin() {
		
		adminrepository.deleteAll();
		
	}
		
		
}
	
	

