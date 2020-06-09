package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Clients;

import com.example.demo.repository.ClientsRepository;



@CrossOrigin
@RestController
public class ClientsController {
	


	
	@Autowired
	private ClientsRepository clientsRepository;
	
	

	@PostMapping("/clientscheklist")
    public ResponseEntity<Clients> createEmployee(@RequestBody Clients client) {
        
		Clients client1 = clientsRepository.save(client);
		
		return new ResponseEntity<Clients>(client1,HttpStatus.CREATED);
    }
	
	
	@GetMapping(path="/clientscheklist")
	public List<Clients> getAllClients(){
		
		List<Clients> clientslist = new ArrayList();
		
		clientsRepository.findAll().forEach(clientslist :: add);
		
		return clientslist;
		
	}
	
	
	
	
	
	
	    
}
