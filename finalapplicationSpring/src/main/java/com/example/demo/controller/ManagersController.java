package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Managers;
import com.example.demo.entity.Organizations;
import com.example.demo.functions.ManagersFunctionsImpl;
import com.example.demo.repository.ManagersRepository;
import com.example.demo.repository.OrganizationsRepository;


@CrossOrigin
@RestController
public class ManagersController {

	
	
	
	@Autowired
	private ManagersRepository managersRepo;
	
	@Autowired
	private OrganizationsRepository organizationRepo;
	
	@Autowired
	private ManagersFunctionsImpl managersFuncImpl;
	
	@PostMapping("managers/managerslist/create")
    public ResponseEntity<Managers> createManagers(@Valid @RequestBody Managers manager) {
		

		managersRepo.save(manager);
		
		return new ResponseEntity<Managers>(manager,HttpStatus.CREATED);
    }
	

	
	
	
}
