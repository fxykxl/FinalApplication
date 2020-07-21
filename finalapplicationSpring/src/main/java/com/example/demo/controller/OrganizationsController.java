package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Clients;
import com.example.demo.entity.Managers;
import com.example.demo.entity.Organizations;
import com.example.demo.functions.OrganizationsFunctionsImp;
import com.example.demo.repository.ManagersRepository;
import com.example.demo.repository.OrganizationsRepository;

@CrossOrigin
@RestController
public class OrganizationsController {
	
	@Autowired
	private OrganizationsFunctionsImp organizationsFunctionsImp;
	
	@Autowired
	private OrganizationsRepository organizationsRepository;
	
	@Autowired
	private ManagersRepository managersRepository;
	
	
	@PostMapping("/organization/create/{idManager}")
	public ResponseEntity<?> createOrganization(@Valid @RequestBody Organizations organization, @PathVariable String idManager ,BindingResult result) {
		 if(result.hasErrors()) {
				
				Map<String , String> errorMap = new HashMap<>();
				for(FieldError error : result.getFieldErrors()) {
					errorMap.put(error.getField(), error.getDefaultMessage());
				}
				
				return new ResponseEntity<Map<String , String>>(errorMap ,HttpStatus.BAD_REQUEST );
	            
	    }	
		 
		 Managers manager= managersRepository.findByEmail(idManager);
		 if(manager==null) {
			 return new ResponseEntity<String>("You don't have the privilege",HttpStatus.BAD_REQUEST);
		 }
		 
		organization.setIdManager(manager.getEmail());		
		organizationsRepository.save(organization);
		
		return new ResponseEntity<Organizations>(organization,HttpStatus.CREATED);
   }
	
	


	}


