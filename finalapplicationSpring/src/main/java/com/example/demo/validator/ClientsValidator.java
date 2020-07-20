package com.example.demo.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.entity.Clients;

@Component
public class ClientsValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Clients.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		Clients client= (Clients) object;
		
		if(!client.getPassword().equals(client.getConfirmPassword())) {
			errors.rejectValue("confirmPassword", "Match", "Passwords Must Match");
		}
		
	}
	
	

}
