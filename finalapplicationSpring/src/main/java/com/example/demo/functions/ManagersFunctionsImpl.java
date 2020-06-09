package com.example.demo.functions;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Managers;
import com.example.demo.repository.IManagersFunctions;
import com.example.demo.repository.ManagersRepository;




@Service
@Transactional 
public class ManagersFunctionsImpl implements IManagersFunctions{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ManagersRepository managerrepository;

	@Override
	public List<Managers> AfficherManager(String email) {

		String sql ="select * from Managers where email='"+email+"'";
		List<Managers> Listmanagers = jdbcTemplate. query(sql ,
				BeanPropertyRowMapper.newInstance(Managers.class));
		
		return Listmanagers;
	}

	@Override
	public List<Managers> list() {

		String sql ="select * from Managers ";
		List<Managers> Listmanagers = jdbcTemplate. query(sql ,
				BeanPropertyRowMapper.newInstance(Managers.class));
		
		return Listmanagers;
		
	}
	
	@Override
	public void InsertManager (Managers m) {
		
		if(AfficherManager(m.getEmail().toString()).size() != 0)
		{
			System.out.println("Email already exist");
		}
		
		else {
			managerrepository.save(m);
		} 
		
	}
	
	@Override
	public String LoginManager(String email, String password) {
		try {
			if (AfficherManager(email).get(0).getPasswordManager().toString().equals(new MD5().encrypt(password))) 
				
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
	public void Update(String email, String NewfirstName, String NewLastName, int NewPhone, String NewLocation,
			String NewGender, String NewDateNaissance, String DescriptionManager, String NewregristrationDate,
             String NewProName,String Password){
			
		try {
			if (AfficherManager(email).get(0).getPasswordManager().toString().equals(new MD5().encrypt(Password))) 
			{
				String sql ="update Admins set FIRSTNAME='"+NewfirstName+"' ,LASTNAME='"+NewLastName+"',PRONAME='"+NewProName+"' ,PHONE="+NewPhone+" ,"
						+ "ADRESSMANAGER='"+NewLocation+"'  ,GENDER='"+NewGender+"' ,DESCRIPTIONMANAGER='"+DescriptionManager+"' ,"+ 
						"BIRTHDATE='"+NewDateNaissance+"' REGISTRATIONDATE='"+NewregristrationDate+"' where EMAIL="+email;
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
	public void UpdatePassword(String email, String Oldpassword, String Newpassword) {
		try {
			if (AfficherManager(email).get(0).getPasswordManager().toString().equals(new MD5().encrypt(Oldpassword)))
			{
				String sql = "update Managers set PASSWORDMANAGER= '"+new MD5().encrypt(Newpassword)+"' where EMAIL= '"+email +"'";
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
	public void UpdateEmail(String email, String  password, String Newemail) {
		try {
			if (AfficherManager(email).get(0).getPasswordManager().toString().equals(new MD5().encrypt(password)))
			{
				String sql = "update Managers set EMAIL= '"+Newemail+"' where EMAIL= '"+email +"'";
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


	
	
	
	 

}




