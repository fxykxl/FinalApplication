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
import com.example.demo.entity.DeliveryMen;
import com.example.demo.entity.Managers;
import com.example.demo.functions.AdminsFunctionsImpl;
import com.example.demo.functions.ClientsFunctionsImpl;
import com.example.demo.functions.DeliveryManFunctionsImpl;
import com.example.demo.functions.ManagersFunctionsImpl;
import com.example.demo.repository.AdminsRepository;
import com.example.demo.repository.ClientsRepository;
import com.example.demo.repository.DeliveryManRepository;
import com.example.demo.repository.ManagersRepository;



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
	
	@Autowired
	private DeliveryManRepository deliverymenRepository;
	
	@Autowired
	private DeliveryManFunctionsImpl deliveryMenFuncImpl;
	
	@Autowired 
	private ManagersRepository managersRepository;
	
	@Autowired
	private ManagersFunctionsImpl managersFuncImpl;
	
	

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
	
	
	//Admins Functions
	
	
	@PostMapping(path="admin/create")
    public void createAdmin(@RequestBody Admins admin) {
        
		adminsfunctionsimpl.InsertAdmin(admin);
				
    }
	
	
	@PutMapping(path="admin/update/{email}")
	public ResponseEntity<Admins> updateAdmin(@PathVariable String email,@RequestBody Admins admin){
		
	Admins updatedAdmin=adminsRepository.save(admin);
	
	return new ResponseEntity<Admins>(admin,HttpStatus.OK); 
		
		
	}
	
	
	//DeliveryMen Functions
	
	
	
	@PostMapping(path="admin/deliverymenlist/create")
    public ResponseEntity<DeliveryMen> createDeliveryMan(@RequestBody DeliveryMen deliveryMan) {
        
		DeliveryMen deliveryman1 = deliverymenRepository.save(deliveryMan);
		
		return new ResponseEntity<DeliveryMen>(deliveryman1,HttpStatus.CREATED);
    }
	
	
	@GetMapping(path="admin/deliverymenlist")
	public List<DeliveryMen> getAllDeliveryMen(){
		
		List<DeliveryMen> deliveryMenList = new ArrayList<DeliveryMen>();
		
		deliverymenRepository.findAll().forEach(deliveryMenList :: add);
		
		return deliveryMenList;
		
	}
	
	
	@PutMapping(path="admin/deliverymenlist/update/{email}")
	public ResponseEntity<DeliveryMen> updateClient(@PathVariable String email,@RequestBody DeliveryMen deliveryMan){
		DeliveryMen updateClient=deliverymenRepository.save(deliveryMan);
		return new ResponseEntity<DeliveryMen>(deliveryMan,HttpStatus.OK); 
		
		
	}
	
	
	@DeleteMapping(path="admin/deliverymenlist/delete/{email}")
	public void deleteDeleveryMan(@PathVariable String email) {
		
		DeliveryMen deliveryManToBeDeleted = deliveryMenFuncImpl.AfficherDeliveryMan(email).get(0);
		
		adminsfunctionsimpl.DeleteDeliveryMan(deliveryManToBeDeleted);
		
	}
	
	
	@GetMapping("admin/deliverymenlist/{email}")
	public DeliveryMen getSpecificDeliveryMan(@PathVariable String email) {
		
		return deliveryMenFuncImpl.AfficherDeliveryMan(email).get(0);
		
	}
	
	
	//Manager Functions
	
	
	@PostMapping(path="admin/managerslist/create")
    public ResponseEntity<Managers> createManager(@RequestBody Managers manager) {
        
		Managers manager1 = managersRepository.save(manager);
		
		return new ResponseEntity<Managers>(manager1,HttpStatus.CREATED);
    }
	
	
	@GetMapping(path="admin/managerslist")
	public List<Managers> getAllManagers(){
		
		List<Managers> managersList = new ArrayList<Managers>();
		
		managersRepository.findAll().forEach(managersList :: add);
		
		return managersList;
		
	}
	
	
	@PutMapping(path="admin/managerslist/update/{email}")
	public ResponseEntity<Managers> updateManager(@PathVariable String email,@RequestBody Managers manager){
		
		Managers updatedManager=managersRepository.save(manager);
		
		return new ResponseEntity<Managers>(manager,HttpStatus.OK); 
		
		
	}
	
	
	@DeleteMapping(path="admin/managerslist/delete/{email}")
	public void deleteManager(@PathVariable String email) {
		
		Managers managerToBeDeleted = managersFuncImpl.AfficherManager(email).get(0);
		
		adminsfunctionsimpl.DeleteManager(managerToBeDeleted);
		
	}
	
	
	@GetMapping("admin/managerslist/{email}")
	public Managers getSpecificManager(@PathVariable String email) {
		
		return managersFuncImpl.AfficherManager(email).get(0);
		
	}
	
	
	
	
	
	
	
	
	
	

}
