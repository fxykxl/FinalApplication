package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Clients;
import com.example.demo.functions.ClientsFunctionsImpl;
import com.example.demo.repository.ClientsRepository;



@CrossOrigin
@RestController
public class ClientsController {
	
	
	@Autowired
	private ClientsFunctionsImpl clientsFunctionsImpl;
	

	@Autowired
	private ClientsRepository clientsRepository;
	
	

	@PostMapping("client/clientslist/create")
    public ResponseEntity<Clients> createClient(@RequestBody Clients client) {
        
		Clients client1 = clientsRepository.save(client);
		
		return new ResponseEntity<Clients>(client1,HttpStatus.CREATED);
    }
	
	
	
	@GetMapping("client/clientslist/{phone}")
	public Clients getSpecificClient(@PathVariable Long phone) {
		return clientsFunctionsImpl.AfficherClient(phone).get(0);
	}
	
	
	
	
	
	
	
	
	
	
	    
}
