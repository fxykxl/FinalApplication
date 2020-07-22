package com.example.demo.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Clients;
import com.example.demo.entity.Managers;
import com.example.demo.functions.AdminsFunctionsImpl;
import com.example.demo.functions.ClientsFunctionsImpl;
import com.example.demo.payload.LoginRequest;
import com.example.demo.repository.ClientsRepository;



@CrossOrigin
@RestController
public class ClientsController {
	
	
	@Autowired
	private ClientsFunctionsImpl clientsFunctionsImpl;
	

	@Autowired
	private ClientsRepository clientsRepository;
	
	@Autowired
	private AdminsFunctionsImpl adminsfunctionsimpl;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	


	
	@PostMapping("/client/signin")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
    if(result.hasErrors()) {
			
			Map<String , String> errorMap = new HashMap<>();
			for(FieldError error : result.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			
			return new ResponseEntity<Map<String , String>>(errorMap ,HttpStatus.BAD_REQUEST );
            
    }
			
			Clients client= clientsRepository.findByEmail(loginRequest.getEmail());			
			if(bCryptPasswordEncoder.matches(loginRequest.getPassword() ,client.getPasswordClient())) {
				
				return ResponseEntity.ok("Logged in Successfully");
			
			}
						
			return new ResponseEntity<>("There have been Errors" ,HttpStatus.BAD_REQUEST );
			
			
			
    }
    
		
			

	@PostMapping("client/clientslist/create")
    public ResponseEntity<?> createClient(@Valid @RequestBody Clients client , BindingResult result) {
		
		if(result.hasErrors()) {
			
			Map<String , String> errorMap = new HashMap<>();
			for(FieldError error : result.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<Map<String , String>>(errorMap ,HttpStatus.BAD_REQUEST );
		}
		
		if(clientsRepository.existsByPhone(client.getPhone())) {
			return new ResponseEntity<String>("Phone Already Exists",HttpStatus.BAD_REQUEST);
		}
		
		if(clientsRepository.existsByEmail(client.getEmail())) {
			return new ResponseEntity<>("Email Already Exists",HttpStatus.BAD_REQUEST);
		}
				
		
			client.setPasswordClient(bCryptPasswordEncoder.encode(client.getPasswordClient()));	
			
			client.setPhone(client.getPhone());
			
			Clients client1 = clientsRepository.save(client);					
			
			return new ResponseEntity<Clients>(client1,HttpStatus.CREATED);
			

		
    }
	
	
	@PutMapping(path="client/clientslist/update/{phone}")
	public ResponseEntity<?> updateClient(@PathVariable Long phone,@Valid @RequestBody Clients client, BindingResult result){
		
       if(result.hasErrors()) {
			
			Map<String , String> errorMap = new HashMap<>();
			for(FieldError error : result.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			
			return new ResponseEntity<Map<String , String>>(errorMap ,HttpStatus.BAD_REQUEST );
            
    }

		
		Clients newClient= clientsRepository.findByPhone(phone);
		
    	newClient.setFirstName(client.getFirstName());
    	newClient.setLastName(client.getLastName());
    	newClient.setAddressClient(client.getAddressClient());
    	newClient.setBirthDate(client.getBirthDate());
	    newClient.setEmail(client.getEmail());
    	newClient.setInscriptionDate(client.getInscriptionDate());
    	newClient.setPasswordClient(client.getPasswordClient());
	
		 
		final Clients updatedClient= clientsRepository.save(newClient);
		return new ResponseEntity<Clients>(updatedClient,HttpStatus.OK); 
		
		
	}
	
	
	
	
	@GetMapping("client/clientslist/{phone}")
	public Clients getSpecificClient(@PathVariable Long phone) {
		return clientsFunctionsImpl.AfficherClient(phone).get(0);
	}
	
	
	
	
	    
}
