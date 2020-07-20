package com.example.demo.functions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Clients;
import com.example.demo.repository.IClientsFunctions;





@Service
@Transactional 
public class ClientsFunctionsImpl implements IClientsFunctions{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	

	    @Override	
		public List<Clients> AfficherClient(Long phone) {

			String sql ="select * from Clients where PHONE="+phone;
			List<Clients> ListClients = jdbcTemplate. query(sql ,
					BeanPropertyRowMapper.newInstance(Clients.class));
			
			return ListClients;
	}
	
	
	
	@Override
	public List<Clients> list() {
		String sql ="select * from Clients ";
		List<Clients> ListClients = jdbcTemplate. query(sql ,
				BeanPropertyRowMapper.newInstance(Clients.class));
		
		return ListClients;
		
	}
	
	
	
	@Override
	public String LoginClient(String password, Long phone) {
		
		try {
			if (AfficherClient(phone).get(0).getPasswordClient().toString().equals(new MD5().encrypt(password))) 
				
			{
				return "connected";
		    }	
			
			else return "phone or password incorrect";
			} catch (Exception e) {
				
				e.printStackTrace();
			return "erreur de hashage du mot de passe";
			}
	}
	
	@Override
	public void UpdatePassword(Long phone, String Oldpassword, String Newpassword) {

		try {
			if (AfficherClient(phone).get(0).getPasswordClient().toString().equals(new MD5().encrypt(Oldpassword)))
			{
				String sql = "update Clients set PASSWORDCLIENT = '"+new MD5().encrypt(Newpassword)+"' where PHONE= "+phone ;
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
	
	/*@Override
	public void Update(Long phone, String NewfirstName, String NewLastName, String NewLocation, String NewEmail,
			String NewGender, String NewDate_Naissance, String Password, String NewinscreptionDate) {

		try {
			if (AfficherClient(phone).get(0).getPasswordClient().toString().equals(new MD5().encrypt(Password))) 
			{
				String sql ="update Clients set FIRSTNAME='"+NewfirstName+"' ,LASTNAME='"+NewLastName+"'  ,"
						+ "ADRESSADMIN='"+NewLocation+"'  ,GENDER='"+NewGender+"' ,"+ "BIRTHDATE='"+NewDate_Naissance+"'"
								+ " INSCREPTIONDATE='"+NewinscreptionDate+"' where PHONE="+phone;
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
	*/
	
	@Override
	public void UpdatePhone(Long phone, String password, String Newphone) {
		
		try {
			if (AfficherClient(phone).get(0).getPasswordClient().toString().equals(new MD5().encrypt(password)))
			{
				String sql = "update Clients set PHONE= '"+Newphone+"' where PHONE= '"+phone;
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

	
	





