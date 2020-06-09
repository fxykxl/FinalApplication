package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Admins;
import com.example.demo.entity.Clients;
import com.example.demo.functions.AdminsFunctionsImpl;
import com.example.demo.functions.ClientsFunctionsImpl;
import com.example.demo.repository.AdminsRepository;
import com.example.demo.repository.ClientsRepository;



@CrossOrigin
@RestController
public class AdminsController {
	
	@Autowired
	private ClientsFunctionsImpl clientsFunctionsImpl;
	
	@Autowired
	private ClientsRepository clientsRepository;
	
	@Autowired
	private AdminsFunctionsImpl adminsfunctionsimpl;
	
	@Autowired
	private AdminsRepository adminsRepository;
	
	

	@PostMapping(path="admin/clientslist/create")
    public ResponseEntity<Clients> createClient(@RequestBody Clients client) {
        
		Clients client1 = clientsRepository.save(client);
		
		return new ResponseEntity<Clients>(client1,HttpStatus.CREATED);
    }
	
	
	@GetMapping(path="admin/clientslist")
	public List<Clients> getAllClients(){
		
		List<Clients> clientslist = new ArrayList<Clients>();
		
		clientsRepository.findAll().forEach(clientslist :: add);
		
		return clientslist;
		
	}
	
	
	@PutMapping(path="admin/clientslist/update/{phone}")
	public ResponseEntity<Clients> updateClient(@PathVariable Long phone,@RequestBody Clients client){
		Clients updateClient=clientsRepository.save(client);
		return new ResponseEntity<Clients>(client,HttpStatus.OK); 
		
		
	}
	
	
	@DeleteMapping(path="admin/clientslist/delete/{phone}")
	public void deleteClient(@PathVariable Long phone) {
		
		Clients clientToBeDeleted = clientsFunctionsImpl.AfficherClient(phone).get(0);
		adminsfunctionsimpl.DeleteClient(clientToBeDeleted);
		
	}
	
	
	@GetMapping("admin/clientslist/{phone}")
	public Clients getSpecificClient(@PathVariable Long phone) {
		return clientsFunctionsImpl.AfficherClient(phone).get(0);
	}
	
	
	@PostMapping(path="admin/create")
    public void createAdmin(@RequestBody Admins admin) {
        
		adminsfunctionsimpl.InsertAdmin(admin);
				
    }
	
	
	@PutMapping(path="admin/update/{email}")
	public ResponseEntity<Admins> updateClient(@PathVariable String email,@RequestBody Admins admin){
		
	Admins updatedAdmin=adminsRepository.save(admin);
	
	return new ResponseEntity<Admins>(admin,HttpStatus.OK); 
		
		
	}
	
	
	
	
	

}
