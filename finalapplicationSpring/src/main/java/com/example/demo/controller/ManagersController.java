package com.example.demo.controller;


import java.util.HashMap;

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


import com.example.demo.entity.Managers;


import com.example.demo.payload.LoginRequest;
import com.example.demo.repository.ManagersRepository;




@CrossOrigin
@RestController
public class ManagersController {

	
	
	
	@Autowired
	private ManagersRepository managersRepo;
	
	
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@PostMapping("manager/managerslist/create")
    public ResponseEntity<?> createManagers(@Valid @RequestBody Managers manager, BindingResult result) {
		 if(result.hasErrors()) {
				
				Map<String , String> errorMap = new HashMap<>();
				for(FieldError error : result.getFieldErrors()) {
					errorMap.put(error.getField(), error.getDefaultMessage());
				}
				
				return new ResponseEntity<Map<String , String>>(errorMap ,HttpStatus.BAD_REQUEST );
	            
	    }
		 
		if(managersRepo.existsByEmail(manager.getEmail())) {
				return new ResponseEntity<>("Email Already Exists",HttpStatus.BAD_REQUEST);
		}
							
		manager.setPasswordManager(bCryptPasswordEncoder.encode(manager.getPasswordManager()));			
		manager.setAccountStatus("Pending for Approval");
		managersRepo.save(manager);
		
		return new ResponseEntity<Managers>(manager,HttpStatus.CREATED);
		
        }
	
	
	
	@PostMapping("/manager/signin")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
    if(result.hasErrors()) {
			
			Map<String , String> errorMap = new HashMap<>();
			for(FieldError error : result.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			
			return new ResponseEntity<Map<String , String>>(errorMap ,HttpStatus.BAD_REQUEST );
            
    }
			
			Managers manager= managersRepo.findByEmail(loginRequest.getEmail());			
			if(bCryptPasswordEncoder.matches(loginRequest.getPassword() ,manager.getPasswordManager())) {
				
				return ResponseEntity.ok("Logged in Successfully");
			
			}
						
			return new ResponseEntity<>("There have been Errors" ,HttpStatus.BAD_REQUEST );
						
			
    }
	
	
	
	@GetMapping("manager/managerslist/{email}")
	public Managers getSpecificManager(@PathVariable String email) {
		return managersRepo.findByEmail(email);
	}
	
	
	
	@PutMapping(path="manager/managerslist/update/{email}")
	public ResponseEntity<?> updateManager(@PathVariable String email,@Valid @RequestBody Managers manager, BindingResult result){
		
       if(result.hasErrors()) {
			
			Map<String , String> errorMap = new HashMap<>();
			for(FieldError error : result.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			
			return new ResponseEntity<Map<String , String>>(errorMap ,HttpStatus.BAD_REQUEST );
            
    }

		
		Managers newManager= managersRepo.findByEmail(manager.getEmail());
		
		newManager.setAddressManager(manager.getAddressManager());
		newManager.setBirthDate(manager.getBirthDate());
		newManager.setEmail(email);
		newManager.setFirstName(manager.getFirstName());
		newManager.setLastName(manager.getLastName());
		newManager.setPasswordManager(bCryptPasswordEncoder.encode(manager.getPasswordManager()));
		newManager.setPhone(manager.getPhone());
		newManager.setProname(manager.getProname());
		 
		final Managers updateManager= managersRepo.save(newManager);
		
	
		return new ResponseEntity<Managers>(updateManager,HttpStatus.OK); 
		
		
	}
	
	
	@DeleteMapping(path="manager/managerslist/delete/{email}")
	public void deleteManager(@PathVariable String email) {	
		Managers managerToBeDeleted = managersRepo.findByEmail(email);	
		managersRepo.delete(managerToBeDeleted);
		
	}
	
	

	
	
	
}
