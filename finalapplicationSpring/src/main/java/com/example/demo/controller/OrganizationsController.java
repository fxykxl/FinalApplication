package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.example.demo.repository.OrganizationsRepository;

@CrossOrigin
@RestController
public class OrganizationsController {
	
	@Autowired
	private OrganizationsFunctionsImp organizationsFunctionsImp;
	
	@Autowired
	private OrganizationsRepository organizationsRepository;
	


	}


