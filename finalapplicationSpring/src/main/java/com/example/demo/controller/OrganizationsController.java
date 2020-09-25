package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.entity.Managers;
import com.example.demo.entity.Organizations;

import com.example.demo.repository.ManagersRepository;
import com.example.demo.repository.OrganizationsRepository;

@CrossOrigin
@RestController
public class OrganizationsController {
	
	
	@Autowired
	private OrganizationsRepository organizationsRepository;
	
	@Autowired
	private ManagersRepository managersRepository;
	
	
	@PostMapping("/organization/organizationslist/create/{idManager}")
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
	

	
	
	@PutMapping("/organization/organizationslist/update/{idOrganization}")
	public ResponseEntity<?> updateOrganization(@Valid @RequestBody Organizations organization, @PathVariable Long idOrganization  ,BindingResult result) {
		 if(result.hasErrors()) {
				
				Map<String , String> errorMap = new HashMap<>();
				for(FieldError error : result.getFieldErrors()) {
					errorMap.put(error.getField(), error.getDefaultMessage());
				}
				
				return new ResponseEntity<Map<String , String>>(errorMap ,HttpStatus.BAD_REQUEST );
	            
	    }
		 
		 Organizations organization2 = organizationsRepository.findByIdOrganization(idOrganization);
		 organization2.setDescription(organization.getDescription());
		 organization2.setLabel(organization.getLabel());
		  
		 final Organizations updatedOrganization =  organizationsRepository.save(organization2);

			
		return new ResponseEntity<Organizations>(updatedOrganization,HttpStatus.OK); 

  }
	
	
	@DeleteMapping(path="/organization/organizationslist/delete/{idOrganization}")
	public ResponseEntity<?> updateOrganization(@PathVariable Long idOrganization) {

		 Organizations organization2 = organizationsRepository.findByIdOrganization(idOrganization);
		 
		 organizationsRepository.delete(organization2);
		 return new ResponseEntity<Organizations>(HttpStatus.OK);
		 
	}

	
	
	
	
	
}


