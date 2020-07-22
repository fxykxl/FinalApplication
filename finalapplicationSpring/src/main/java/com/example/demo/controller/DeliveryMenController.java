package com.example.demo.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Clients;
import com.example.demo.entity.DeliveryMen;
import com.example.demo.entity.Managers;
import com.example.demo.functions.DeliveryManFunctionsImpl;
import com.example.demo.payload.LoginRequest;
import com.example.demo.repository.DeliveryManRepository;

@CrossOrigin
@RestController
public class DeliveryMenController {
	
	@Autowired
	private DeliveryManRepository deliverymanRepo;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private DeliveryManFunctionsImpl deliveryMenFuncImpl;
	
	
	@PostMapping("/deliveryman/signin")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
    if(result.hasErrors()) {
			
			Map<String , String> errorMap = new HashMap<>();
			for(FieldError error : result.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			
			return new ResponseEntity<Map<String , String>>(errorMap ,HttpStatus.BAD_REQUEST );
            
    }
			
			DeliveryMen deliveryMan= deliverymanRepo.findByEmail(loginRequest.getEmail());			
			if(bCryptPasswordEncoder.matches(loginRequest.getPassword() ,deliveryMan.getPasswordDeliverMan())) {
				
				return ResponseEntity.ok("Logged in Successfully");
			
			}
						
			return new ResponseEntity<>("There have been Errors" ,HttpStatus.BAD_REQUEST );
			
			
			
    }
	
	@PostMapping(path="deliveryman/deliverymenlist/create")
    public ResponseEntity<?> createDeliveryMan(@Valid @RequestBody DeliveryMen deliveryMan,BindingResult result) {				
        
     if(result.hasErrors()) {
			
			Map<String , String> errorMap = new HashMap<>();
			for(FieldError error : result.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<Map<String , String>>(errorMap ,HttpStatus.BAD_REQUEST );
		}
			
		
		if(deliverymanRepo.existsByEmail(deliveryMan.getEmail())) {
			return new ResponseEntity<>("Email Already Exists",HttpStatus.BAD_REQUEST);
		}
				
		
		deliveryMan.setPasswordDeliverMan(bCryptPasswordEncoder.encode(deliveryMan.getPasswordDeliverMan()));	
			
		deliveryMan.setPhone(deliveryMan.getPhone());
			
		DeliveryMen deliveryMan2 = deliverymanRepo.save(deliveryMan);					
			
		return new ResponseEntity<DeliveryMen>(deliveryMan2,HttpStatus.CREATED);
			

		
    }
	
	
	
	@GetMapping("deliveryman/deliverymenlist/{email}")
	public DeliveryMen getSpecificDeliveryMan(@PathVariable String email) {
		
		return deliveryMenFuncImpl.AfficherDeliveryMan(email).get(0);
		
	}
	
	
	
	
	@PutMapping(path="deliveryman/deliverymenlist/update/{email}")
	public ResponseEntity<?> updateDeliveryMan(@PathVariable String email,@Valid @RequestBody DeliveryMen deliveryMan, BindingResult result){
		
	       if(result.hasErrors()) {
				
				Map<String , String> errorMap = new HashMap<>();
				for(FieldError error : result.getFieldErrors()) {
					errorMap.put(error.getField(), error.getDefaultMessage());
				}
				
				return new ResponseEntity<Map<String , String>>(errorMap ,HttpStatus.BAD_REQUEST );
	            
	    }

			
	       DeliveryMen newDeliveryMan= deliverymanRepo.findByEmail(deliveryMan.getEmail());
			
	       newDeliveryMan.setAddressDeliveryMan(deliveryMan.getAddressDeliveryMan());
	       newDeliveryMan.setBirthDate(deliveryMan.getBirthDate());
	       newDeliveryMan.setEmail(email);
	       newDeliveryMan.setFirstName(deliveryMan.getFirstName());
	       newDeliveryMan.setLastName(deliveryMan.getLastName());
	       newDeliveryMan.setPasswordDeliverMan(deliveryMan.getPasswordDeliverMan());
	       newDeliveryMan.setPhone(deliveryMan.getPhone());
	
			 
			final DeliveryMen updateDeliveryMan= deliverymanRepo.save(newDeliveryMan);
			
		
			return new ResponseEntity<DeliveryMen>(updateDeliveryMan,HttpStatus.OK); 
			
			
		}
	}


