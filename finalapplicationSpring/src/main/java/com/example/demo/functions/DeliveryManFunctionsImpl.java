package com.example.demo.functions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.DeliveryMen;
import com.example.demo.repository.DeliveryManRepository;
import com.example.demo.repository.IDeliveryManFunctions;




@Service
@Transactional 
public class DeliveryManFunctionsImpl implements IDeliveryManFunctions{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private DeliveryManRepository deliverymanrepository;
	
	@Override
	public List<DeliveryMen> AfficherDeliveryMan(String email) {
			
		String sql ="select * from DELIVERYMAN where EMAIL ='"+email+"'" ;
		List<DeliveryMen> Listdeliveryman = jdbcTemplate. query(sql ,
				BeanPropertyRowMapper.newInstance(DeliveryMen.class));
		
		return Listdeliveryman;
	}
	


	@Override
	public List<DeliveryMen> list() {
		String sql ="select * from DELIVERYMAN ";
		List<DeliveryMen> Listdeliveryman = jdbcTemplate. query(sql ,
				BeanPropertyRowMapper.newInstance(DeliveryMen.class));
		
		return Listdeliveryman;
	}

	@Override
	public String LoginDeliveryMan(String email, String password) {
		try {
			if (AfficherDeliveryMan(email).get(0).getPasswordDeliverMan().toString().equals(new MD5().encrypt(password))) 
				
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
	public void InsertDeliveryMan (DeliveryMen d) {
		
		if(AfficherDeliveryMan(d.getEmail().toString()).size() != 0)
		{
			System.out.println("Email already exist");
		}
		
		else {
			deliverymanrepository.save(d);
		} 
		
	}

	@Override
	public void UpdatePassword(String email, String Oldpassword, String Newpassword) {
		try {
			if (AfficherDeliveryMan(email).get(0).getPasswordDeliverMan().toString().equals(new MD5().encrypt(Oldpassword)))
			{
				String sql = "update DELIVERYMAN set PASSWORDDELIVERYMAN= '"+new MD5().encrypt(Newpassword)+"' where EMAIL= '"+email +"'";
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
	public void Update(String email, String NewfirstName, String NewLastName, int NewPhone, String NewLocation,
			 String NewGender, String NewDate_Naissance, String Password,
			String NewregistrationDate) {
		try {
			if (AfficherDeliveryMan(email).get(0).getPasswordDeliverMan().toString().equals(new MD5().encrypt(Password))) 
			{
				String sql ="update DELIVERYMAN set FIRSTNAME='"+NewfirstName+"' ,LASTNAME='"+NewLastName+"' ,PHONE="+NewPhone+" ,"
						+ "ADRESSDELIVERYMAN='"+NewLocation+"'  ,GENDER='"+NewGender+"' ,"+ 
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
			if (AfficherDeliveryMan(email).get(0).getPasswordDeliverMan().toString().equals(new MD5().encrypt(password)))
			{
				String sql = "update DELIVERYMAN set EMAIL= '"+Newemail+"' where EMAIL= '"+email +"'";
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
		